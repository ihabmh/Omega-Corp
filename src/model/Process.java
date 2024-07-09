package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Process {
    private int id;
    private String name;
    private List<HumanResource> humanResources = new ArrayList<>();
    private List<MaterialResource> materialResources = new ArrayList<>();
    private List<EquipmentResource> equipmentResources = new ArrayList<>();
    private double duration;
    private Status status;
    private double cost;

    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED
    }

    private static List<Process> allProcesses = new ArrayList<>();
    private static Map<Integer, Process> processMap = new HashMap<>();

    public Process(int id, String name, double duration, List<HumanResource> humanResources, List<MaterialResource> materialResources,
                   List<EquipmentResource> equipmentResources, double cost, Status status) {
        this.id = id;
        this.name = name;
        this.humanResources = humanResources;
        this.materialResources = materialResources;
        this.equipmentResources = equipmentResources;
        this.duration = duration;
        this.status = status;
        this.cost = cost;
    }

    public static Process createProcess(String name, double duration, List<HumanResource> selectedHumanResource, List<MaterialResource> selectedMaterialResource,
                                        List<EquipmentResource> selectedEquipmentResource) {
        int id = allProcesses.size() + 1;
        double cost = MaterialResource.calculateResourceCost(selectedMaterialResource) + HumanResource.calculateEmployeeCost(selectedHumanResource, duration) +
                EquipmentResource.calculateEquipmentCost(selectedEquipmentResource);
        Process newProcess = new Process(id, name, duration, selectedHumanResource, selectedMaterialResource, selectedEquipmentResource, cost, Status.NOT_STARTED);

        allProcesses.add(newProcess);
        processMap.put(id, newProcess);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\process.txt", true))) {
            List<Integer> hrIds = selectedHumanResource.stream().map(HumanResource::getId).collect(Collectors.toList());
            List<Integer> mrIds = selectedMaterialResource.stream().map(MaterialResource::getId).collect(Collectors.toList());
            List<Integer> erIds = selectedEquipmentResource.stream().map(EquipmentResource::getId).collect(Collectors.toList());

            writer.printf("%-3d| %-23s| %-17s| %-20s| %-21s| %-8.0f| %-7s| %.2f%n",
                    newProcess.getId(),
                    newProcess.getName(),
                    hrIds.toString(),
                    mrIds.toString(),
                    erIds.toString(),
                    newProcess.getDuration(),
                    newProcess.getStatus(),
                    newProcess.getCost());
        } catch (IOException e) {
            System.err.println("Error writing to process.txt: " + e.getMessage());
        }

        return newProcess;
    }

    public static void initializeExistingProcesses(List<Process> existingProcesses) {
        int id = existingProcesses.size() + 1;
        allProcesses.addAll(existingProcesses);
        for (Process process : existingProcesses) {
            processMap.put(process.getId(), process);
            if (process.getId() >= id) {
                id = process.getId() + 1;
            }
        }
    }


    public void removeProcess() {
        // Implementation to remove this process
    }

    public Process getProcess() {
        return this;
    }

    public void addResource(EquipmentResource resource) {
        equipmentResources.add(resource);
    }

//    public void assignEmployee(HumanResource employee) {
//        HumanResource.add(employee);
//    }

//    public void updateStatus(String newStatus) {
//        this.status = Status.valueOf(newStatus.toUpperCase());
//    }

//    public double calculateCost() {
//        double totalCost = 0.0;
//        for (EquipmentResource resource : equipmentResources) {
//            totalCost += resource.calculateEquipmentCost(10); // Assuming 10 units for each resource
//        }
//        this.cost = totalCost;
//        return totalCost;
//    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void execute() throws InterruptedException {
        // Check if the status is already COMPLETED
        if (getStatus() == Status.COMPLETED) {
            System.out.println("Process: " + name + " is already completed.");
            return; // Exit the method if the process is already completed
        }

        setStatus(Status.IN_PROGRESS);
        updateStatusInFile(this.getId(), Status.IN_PROGRESS);
        System.out.println("Starting process: " + name);
        long startTime = System.currentTimeMillis();
        Thread.sleep((long) duration); // simulate the process duration
        long endTime = System.currentTimeMillis();
        setStatus(Status.COMPLETED);
        System.out.println("Completed process: " + name + " in " + (endTime - startTime) + " ms");

        // Update the status in the file
        updateStatusInFile(this.getId(), Status.COMPLETED);
    }

    public void updateStatusInFile(int processId, Status newStatus) {
        File file = new File("src\\data\\process.txt");
        File tempFile = new File("src\\data\\process_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(String.valueOf(processId))) {
                    // Update the status of the process with the given ID
                    String[] columns = line.split("\\|");
                    columns[6] = " " + newStatus.toString();  // Assuming the status is in the 7th column
                    line = String.join("|", columns);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the old file with the new file
        if (!file.delete()) {
            System.out.println("Could not delete the old file");
            return;
        }
        if (!tempFile.renameTo(file)) {
            System.out.println("Could not rename the temporary file");
        }
    }

    public String getName() {
        return name;
    }

    public List<HumanResource> getEmployees() {
        return humanResources;
    }
    public List<EquipmentResource> getEquipmentResources() {
        return equipmentResources;
    }
    public List<MaterialResource> getMaterialResources() {
        return materialResources;
    }

    public double getDuration() {
        return duration;
    }

    public Status getStatus() {
        return status;
    }

    public double getCost() {
        return cost;
    }



    public void addMaterialResource(MaterialResource resource) {
        materialResources.add(resource);
    }

    // toString method for better representation of the process
    @Override
    public String toString() {
        return "model.Process{id=" + id + ", name='" + name + "', duration=" + duration + ", status='" + status + "', cost=" + cost + "}";
    }

    public Map<String, Long> getMaterialResourceCountsByName() {
        return materialResources.stream()
                .collect(Collectors.groupingBy(
                        MaterialResource::getName,
                        Collectors.counting()
                ));
    }

    public Map<String, Long> getEquipmentResourceCountsByName() {
        return equipmentResources.stream()
                .collect(Collectors.groupingBy(
                        EquipmentResource::getName,
                        Collectors.counting()
                ));
    }
}
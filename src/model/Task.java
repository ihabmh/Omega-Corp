package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {
    TaskName name;
    private int id;
    private List<Process> processes = new ArrayList<>();
    private int duration;
    private double progressiveCost;
    private Status status;


    private static List<Task> allTasks;
    private static Map<Integer, Task> taskMap = new HashMap<>();


    public enum TaskName {
        CONCEPTION,
        RAW_MATERIAL_PREPARATION,
        COMPONENT_MANUFACTURING,
        ASSEMBLY_FINISHING,
        TESTING

    }
    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED
    }


    public Task(int id, TaskName name, List<Process> processes) {
        this.id = id;
        this.name = name;
        this.status = Status.NOT_STARTED;
        this.progressiveCost = 0;
        this.processes = processes != null ? processes : new ArrayList<>();
    }

    public static Task createTask(TaskName name) {

        int id = allTasks.size() + 1;
        System.out.println(id);
        Task newTask = new Task(id, name, null);

        allTasks.add(newTask);
        taskMap.put(id, newTask);


        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\task.txt", true))) {
            List<Integer> processesIds = new ArrayList<>();
            if (newTask.getProcesses() != null) {
                for (Process process : newTask.getProcesses()) {
                    processesIds.add(process.getId());
                }
            }
            writer.printf("%-3d| %-15s| %s%n",
                    newTask.getId(),
                    newTask.getName(),
                    processesIds.toString());
        } catch (IOException e) {
            System.err.println("Error writing to task.txt: " + e.getMessage());
        }

        return newTask;
    }

    public static void updateTaskFileWithNewProcess(int taskId, int newProcessId) {
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\data\\task.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(String.valueOf(taskId))) {
                        String[] parts = line.split("\\|");
                        String processIds = parts[2].trim();
                        if (processIds.equals("[]")) {
                            processIds = "[" + newProcessId + "]";
                        } else {
                            processIds = processIds.substring(0, processIds.length() - 1) + ", " + newProcessId + "]";
                        }
                        line = String.format("%-3d| %-25s| %s",
                                taskId,
                                parts[1].trim(),
                                processIds);
                    }
                    lines.add(line);
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\task.txt"))) {
                for (String line : lines) {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating task.txt: " + e.getMessage());
        }
    }

    public static void initializeExistingTasks(List<Task> existingTasks) {
        int id = existingTasks.size() + 1;
        if (allTasks == null){
            allTasks = new ArrayList<>();
        }
        allTasks.addAll(existingTasks);
        for (Task task : existingTasks) {
            taskMap.put(task.getId(), task);
            if (task.getId() >= id) {
                id = task.getId() + 1;
            }
            task.calculateCost();
            task.calculateDuration();
            task.calculateStatus();
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskName getName() {
        return name;
    }

    public void setName(TaskName name) {
        this.name = name;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getProgressiveCost() {
        return progressiveCost;
    }

    public void setProgressiveCost(double progressiveCost) {
        this.progressiveCost = progressiveCost;
    }


    public void calculateStatus() {
        Status status = Status.NOT_STARTED;

        for (Process process : processes) {
            if (process.getStatus().toString().equals(Status.NOT_STARTED.toString()) ) {
                status = Status.NOT_STARTED;
                break; // No need to check further if any process is NOT_STARTED
            } else if (process.getStatus().toString().equals(Status.IN_PROGRESS.toString()) ) {
                status = Status.IN_PROGRESS; // Update to IN_PROGRESS if at least one process is IN_PROGRESS
            } else if (process.getStatus().toString().equals(Status.COMPLETED.toString()) ) {
                status = status == Status.NOT_STARTED ? Status.COMPLETED : status;
            }
        }


        this.status = status;
    }






    // Method to add a process to the task
    public void addProcess(Process process) {
        if (process != null) {
            this.processes.add(process);
            updateDurationAndCost();
        }
    }

    // Method to remove a process from the task
    public void removeProcess(Process process) {
        if (process != null && this.processes.remove(process)) {
            updateDurationAndCost();
        }
    }

//    Method to calculate the total cost of the task
    public double calculateCost() {
        double totalCost = 0.0;
        for (Process process : processes) {
            totalCost += process.getCost();
        }
        this.progressiveCost = totalCost;
        return totalCost;
    }

    // Method to calculate the total duration of the task
    public int calculateDuration() {
        int totalDuration = 0;
        for (Process process : processes) {
            totalDuration += process.getDuration();
        }
        this.duration = totalDuration;
        return totalDuration;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }


    public void execute() throws InterruptedException {
        setStatus(Status.IN_PROGRESS);
        System.out.println("Starting task: " + name);
        for (Process process : processes) {
            process.execute(); // processes execute sequentially
        }
        setStatus(Status.COMPLETED);
        System.out.println("Completed task: " + name);
    }


    private void updateDurationAndCost() {
        calculateCost();
        calculateDuration();
    }

    public  Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processes=" + processes +
                ", duration=" + duration +
                ", progressiveCost=" + progressiveCost +
                ", status=" + status +
                '}';
    }
}



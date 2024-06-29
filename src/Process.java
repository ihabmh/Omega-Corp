import classes.Resource;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private int id;
    private String name;
    private List<Resource> resources;
    private List<HumanResource> employees;
    private int duration;
    private String status;
    private double cost;

    public Process(int id, String name) {
        this.id = id;
        this.name = name;
        this.resources = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.duration = 0;
        this.status = "Not started";
        this.cost = 0.0;
    }

    public void removeProcess() {
        // Implementation to remove this process
    }

    public Process getProcess() {
        return this;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void assignEmployee(HumanResource employee) {
        employees.add(employee);
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public double calculateCost() {
        double totalCost = 0.0;
        for (Resource resource : resources) {
            totalCost += resource.calculateMaterialCost(10); // Assuming 10 units for each resource
        }
        this.cost = totalCost;
        return totalCost;
    }

    public int calculateDuration() {
        int totalDuration = 0;
        for (Resource resource : resources) {
            totalDuration += resource.getDefaultDuration(10); // Assuming 10 units for each resource
        }
        for (HumanResource employee : employees) {
            totalDuration += employee.getTotalHoursWorked();
        }
        this.duration = totalDuration;
        return totalDuration;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public List<HumanResource> getEmployees() {
        return employees;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public double getCost() {
        return cost;
    }

    // toString method for better representation of the process
    @Override
    public String toString() {
        return "Process{id=" + id + ", name='" + name + "', duration=" + duration + ", status='" + status + "', cost=" + cost + "}";
    }
}

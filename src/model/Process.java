package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Process {
    private int id;
    private String name;
    private List<HumanResource> humanResources;
    private List<MaterialResource> materialResources;
    private List<EquipmentResource> equipmentResources;
    private int duration;
    private Status status;
    private double cost;

    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED
    }

    public Process(int id, String name, List<HumanResource> humanResources, List<MaterialResource> materialResources, List<EquipmentResource> equipmentResources, int duration, Status status, double cost) {
        this.id = id;
        this.name = name;
        this.humanResources = humanResources;
        this.materialResources = materialResources;
        this.equipmentResources = equipmentResources;
        this.duration = duration;
        this.status = status;
        this.cost = cost;
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

    public double calculateCost() {
        // Assuming you have a method in model.EquipmentResource to calculate cost
        double totalCost = 0.0;
        for (EquipmentResource resource : equipmentResources) {
            totalCost += resource.calculateMaterialCost(10); // Assuming 10 units for each resource
        }
        this.cost = totalCost;
        return totalCost;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public List<HumanResource> getEmployees() {
//        return HumanResource;
//    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status.toString();
    }

    public double getCost() {
        return cost;
    }

    public List<MaterialResource> getMaterialResources() {
        return materialResources;
    }

    public void addMaterialResource(MaterialResource resource) {
        materialResources.add(resource);
    }

    // toString method for better representation of the process
    @Override
    public String toString() {
        return "model.Process{id=" + id + ", name='" + name + "', duration=" + duration + ", status='" + status + "', cost=" + cost + "}";
    }


}
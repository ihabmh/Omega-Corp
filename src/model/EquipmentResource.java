package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentResource implements Resource {
    private int id;
    private String name;
    private double costPerUnit;

    private static List<EquipmentResource> allEr = new ArrayList<>();
    private static Map<Integer, EquipmentResource> erMap = new HashMap<>();

    public EquipmentResource(int id, String name, double costPerUnit) {
        this.id = id;
        this.name = name;
        this.costPerUnit = costPerUnit;
        allEr.add(this);
        erMap.put(this.id, this);
    }

    public static EquipmentResource createEr(String name, double costPerUnit) {
        int id = allEr.size() + 1;
        EquipmentResource newEr = new EquipmentResource(id, name, costPerUnit);
        allEr.add(newEr);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\equipment-resource.txt", true))) {
            writer.printf("%-3d| %-15s| %s%n",
                    newEr.getId(),
                    newEr.getName(),
                    newEr.getCostPerUnit());
        } catch (IOException e) {
            System.err.println("Error writing to equipment-resource.txt: " + e.getMessage());
        }

        return newEr;
    }

    public static void initializeExistingEr(List<EquipmentResource> existingEr) {
        int id = existingEr.size() + 1;
        allEr.addAll(existingEr);
        for (EquipmentResource er : existingEr) {
            erMap.put(er.getId(), er);
            if (er.getId() >= id) {
                id = er.getId() + 1;
            }
        }
    }

    public static List<EquipmentResource> getAllEquipmentResources() {
        return new ArrayList<>(allEr);
    }
    public static EquipmentResource getEquipmentResourceByName(String name) {
        return erMap.get(name);
    }

    public static EquipmentResource getById(int id) {
        return erMap.get(id);
    }

    public static double calculateEquipmentCost(List<EquipmentResource> allEr) {
        double cost = 0;
        for (EquipmentResource Er : allEr) {
            cost += Er.getCostPerUnit();
        }
        return cost;
    }

    @Override
    public double calculateCost(int units) {
        return units * costPerUnit;
    }

    public int getDefaultDuration(int units) {
        // Example logic: 1 unit of equipment takes 1 hour
        return units;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }
}
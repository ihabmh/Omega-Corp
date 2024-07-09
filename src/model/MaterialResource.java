package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialResource implements Resource {
    private int id;
    private String name;
    private double costPerUnit;

    private static List<MaterialResource> allMr = new ArrayList<>();
    private static Map<Integer, MaterialResource> mrMap = new HashMap<>();

    public MaterialResource(int id, String name, double costPerUnit) {
        this.id = id;
        this.name = name;
        this.costPerUnit = costPerUnit;
        allMr.add(this);
        mrMap.put(this.id, this);
    }


    public static MaterialResource createMr(String name, double costPerUnit) {
        int id = allMr.size() + 1;
        MaterialResource newMr = new MaterialResource(id, name, costPerUnit);
        allMr.add(newMr);
        mrMap.put(id, newMr);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\material-resource.txt", true))) {

            writer.printf("%-3d| %-15s| %s%n",
                    newMr.getId(),
                    newMr.getName(),
                    newMr.getCostPerUnit());
        } catch (IOException e) {
            System.err.println("Error writing to material-resource.txt: " + e.getMessage());
        }

        return newMr;
    }

    public static void initializeExistingMr(List<MaterialResource> existingMr) {
        int id = existingMr.getLast().getId() + 1;
        allMr.addAll(existingMr);
        for (MaterialResource mr : existingMr) {
            mrMap.put(mr.getId(), mr);
            if (mr.getId() >= id) {
                id = mr.getId() + 1;
            }
        }
    }
    public static double calculateResourceCost(List<MaterialResource> allMr) {
        double cost = 0;
        for (MaterialResource mr : allMr) {
            cost += mr.getCostPerUnit();
        }
        return cost;
    }

    public static List<MaterialResource> getAllMaterialResources() {
        return allMr;
    }
    public static MaterialResource getMaterialResourceByName(String name) {
        for (MaterialResource mr : allMr) {
            if (mr.getName().equals(name)) {
                return mr;
            }

        }
        return null;
    }

    public static MaterialResource getById(int id) {
        return mrMap.get(id);
    }

    @Override
    public double calculateCost(int units) {
        return units * costPerUnit;
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
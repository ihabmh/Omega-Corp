package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentResource  {
    private int id;
    private String name;
    private double costPerUnit;

    public EquipmentResource(int id, String name, double costPerUnit) {
        this.id = id;
        this.name = name;
        this.costPerUnit = costPerUnit;
    }


    public double calculateEquipmentCost(int units) {
        return units * costPerUnit;
    }


    public int getDefaultDuration(int units) {
        // Example logic: 1 unit of equipment takes 1 hour
        return units;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

}

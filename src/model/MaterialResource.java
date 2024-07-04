package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MaterialResource {
    private int id;
    private String name;
    private double costPerUnit;

    public MaterialResource(int id, String name, double costPerUnit) {
        this.id = id;
        this.name = name;
        this.costPerUnit = costPerUnit;
    }




    public double calculateMaterialCost(int units) {
        return units * costPerUnit;
    }


    public int getDefaultDuration(int units) {
        // Example logic: 10 units of material takes 1 hour
        return units / 10;
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



package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HumanResource implements Resource {
    private int id;
    private String name;
    private String jobTitle;
    private double hourlyRate;

    private static List<HumanResource> allHr = new ArrayList<>();
    private static Map<Integer, HumanResource> hrMap = new HashMap<>();

    public HumanResource(int id, String name, String jobTitle, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.hourlyRate = hourlyRate;
        allHr.add(this);
        hrMap.put(this.id, this);
    }

    public static HumanResource createHr(String name, String jobTitle, double hourlyRate) {
        int id = allHr.size() + 1;
        HumanResource newHr = new HumanResource(id, name, jobTitle, hourlyRate);
        allHr.add(newHr);
        hrMap.put(id, newHr);


        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\human-resource.txt", true))) {
            writer.printf("%-3d| %-15s| %-15s| %s%n",
                    newHr.getId(),
                    newHr.getName(),
                    newHr.getJobTitle(),
                    newHr.getHourlyRate());
        } catch (IOException e) {
            System.err.println("Error writing to human-resource.txt: " + e.getMessage());
        }
        return newHr;
    }

    public static void initializeExistingHr(List<HumanResource> existingHr) {
        int id = existingHr.size() + 1;
        allHr.addAll(existingHr);
        for (HumanResource hr : existingHr) {
            hrMap.put(hr.getId(), hr);
            if (hr.getId() >= id) {
                id = hr.getId() + 1;
            }
        }
    }

    public static double calculateEmployeeCost(List<HumanResource> allHr, double duration) {
        double cost = 0;
        for (HumanResource hr : allHr) {
            cost += hr.calculateCost((int)duration /1000);
        }
        return cost;
    }

    public static HumanResource getHrByName(String name) {
        for (HumanResource hr : allHr) {
            if (hr.getName().equals(name)) {
                return hr;
            }
        }
        return null;
    };
    public static List<HumanResource> getAllHumanResources() {
        return allHr;
    }

    public static HumanResource getById(int id) {
        return hrMap.get(id);
    }

    @Override
    public double calculateCost(int hours) {
        return hours * this.getHourlyRate();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
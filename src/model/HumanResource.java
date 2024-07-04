package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HumanResource {
    private int id;
    private String name;
    private String jobTitle;
    private double hourlyRate;

    public HumanResource(int id, String name, String jobTitle, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.hourlyRate = hourlyRate;
    }

//    public static model.HumanResource fetchHumanResourceById(int id) {
//        for (model.HumanResource hr : hrList) {
//            if (hr.getId() == id) {
//                return hr;
//            }
//        }
//        return null; // Return null if not found
//    }

//    public void setTotalHoursWorked(int totalHoursWorked) {
//        this.totalHoursWorked = totalHoursWorked;
//    }

    // Getters and Setters for other fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}

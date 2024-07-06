package model;

import ui.Comps.ProjectCardPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
    private static List<Project> allProjects = new ArrayList<>();
    private static Map<Integer, Project> projectMap = new HashMap<>();
    private static int nextId = 1;

    private int id;
    private String name;
    private List<Task> tasks;
    private Status state; // calculated
    private double cost; // calculated
    private Duration duration; // calculated

    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED
    }

    public Project(int id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.state = Status.NOT_STARTED;
        this.cost = 0.0;
        this.duration = Duration.ZERO;
    }

    public static Project createProject(String name) {
        Project newProject = new Project(nextId, name, null);
        allProjects.add(newProject);
        projectMap.put(nextId, newProject);
        nextId++;



        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\project.txt", true))) {
            List<Integer> taskIds = new ArrayList<>();
            if (newProject.getTasks() != null) {
                for (Task task : newProject.getTasks()) {
                    taskIds.add(task.getId());
                }
            }
            writer.printf("%-3d| %-15s| %s%n",
                    newProject.getId(),
                    newProject.getName(),
                    taskIds.toString());
        } catch (IOException e) {
            System.err.println("Error writing to project.txt: " + e.getMessage());
        }

        return newProject;
    }

    public static void initializeExistingProjects(List<Project> existingProjects) {
        allProjects.addAll(existingProjects);
        for (Project project : existingProjects) {
            projectMap.put(project.getId(), project);
            if (project.getId() >= nextId) {
                nextId = project.getId() + 1;
            }
        }
    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public static List<Project> getAllProjects() {
        return allProjects;
    }

    public static Map<Integer, Project> getProjectMap() {
        return projectMap;
    }
}
package model;

import ui.Comps.ProjectCardPanel;

import javax.swing.*;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
    private static List<Project> allProjects = new ArrayList<>();
    private static Map<Integer, Project> projectMap = new HashMap<>();

    private int id;
    private String name;
    private List<Task> tasks;
    private Status status; // calculated
    private double progressiveCost; // calculated
    private double duration; // calculated

    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED
    }

    public Project(int id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        if (tasks != null) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<>();
        }
        this.status = Status.NOT_STARTED;
        this.progressiveCost = 0.0;
        this.duration = 0.0;
    }

    public static Project createProject(String name) {
        int id = allProjects.size() + 1;
        Project newProject = new Project(id, name, null);
        allProjects.add(newProject);
        projectMap.put(id, newProject);




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

    public static void updateProjectFileWithNewTask(int projectId, int newTaskId) {
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\data\\project.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(String.valueOf(projectId))) {
                        String[] parts = line.split("\\|");
                        String taskIds = parts[2].trim();
                        if (taskIds.equals("[]")) {
                            taskIds = "[" + newTaskId + "]";
                        } else {
                            taskIds = taskIds.substring(0, taskIds.length() - 1) + ", " + newTaskId + "]";
                        }
                        line = String.format("%-3d| %-15s| %s",
                                projectId,
                                parts[1].trim(),
                                taskIds);
                    }
                    lines.add(line);
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("src\\data\\project.txt"))) {
                for (String line : lines) {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating project.txt: " + e.getMessage());
        }
    }

    public static void initializeExistingProjects(List<Project> existingProjects) {
        int id = existingProjects.size() + 1;
        allProjects.addAll(existingProjects);

        for (Project project : existingProjects) {
            projectMap.put(project.getId(), project);
            if (project.getId() >= id) {
                id = project.getId() + 1;
            }
            project.calculateCost();
            project.calculateDuration();
            project.calculateStatus();
        }
    }

    public void calculateStatus() {
        // Start with the initial status of NOT_STARTED
        Project.Status status = Project.Status.NOT_STARTED;

        for (Task task : tasks) {
            // Compare the status using the .equals() method
            if (task.getStatus().toString().equals(Task.Status.NOT_STARTED.toString())) {
                status = Project.Status.NOT_STARTED;
                break; // Exit early if there is any project with NOT_STARTED status
            } else if (task.getStatus().toString().equals(Task.Status.IN_PROGRESS.toString()) ) {
                status = Project.Status.IN_PROGRESS; // Update to IN_PROGRESS if at least one project is IN_PROGRESS
            } else if (task.getStatus().toString().equals(Task.Status.COMPLETED.toString()) ) {
                // Only set to COMPLETED if the current status is NOT_STARTED
                if (status == Project.Status.NOT_STARTED) {
                    status = Project.Status.COMPLETED;
                }
            }
        }

        // Set the final calculated status
        this.status = status;
    }


    public void addTask(Task task){
        if(tasks == null){
            tasks = new ArrayList<>();
            tasks.add(task);
        } else {
            tasks.add(task);
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

    public Status getStatus() {
        return status;
    }



    public double getCost() {
        return progressiveCost;
    }

    public void setCost(double cost) {
        this.progressiveCost = cost;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public static List<Project> getAllProjects() {
        return allProjects;
    }



    public double calculateCost() {
        double totalCost = 0.0;
        for (Task task : tasks) {
            totalCost += task.getProgressiveCost();
        }
        this.progressiveCost = totalCost;
        return totalCost;
    }

    // Method to calculate the total duration of the task
    public int calculateDuration() {
        int totalDuration = 0;
        for (Task task : tasks) {
            totalDuration += task.getDuration();
        }
        this.duration = totalDuration;
        return totalDuration;
    }

    public static Map<Integer, Project> getProjectMap() {
        return projectMap;
    }
    public void setStatus(Project.Status status) {
        this.status = status;
    }


    public void execute() throws InterruptedException {
        setStatus(Status.IN_PROGRESS);
        System.out.println("Starting project: " + name);
        for (Task task : tasks) {
            task.execute(); // tasks execute sequentially
        }
        setStatus(Status.COMPLETED);
        System.out.println("Completed project: " + name);
    }
}
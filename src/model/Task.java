package model;

import java.util.ArrayList;
import java.util.List;

public class Task {
    TaskName name;
    private int id;
    private List<Process> processes;
    private int duration;
    private double progressiveCost;
    private Status status;

    public enum TaskName {
        CONCEPTION,
        RAW_MATERIAL_PREPARATION,
        COMPONENT_MANUFACTURING,
        ASSEMBLY_FINISHING,
        TESTING

    }
    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED
    }

    // Parameterized constructor
    public Task(int id, TaskName name, List<Process> processes) {
        this.id = id;
        this.name = name;
        this.processes = processes != null ? processes : new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskName getName() {
        return name;
    }

    public void setName(TaskName name) {
        this.name = name;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getProgressiveCost() {
        return progressiveCost;
    }

    public void setProgressiveCost(double progressiveCost) {
        this.progressiveCost = progressiveCost;
    }

//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }

    // Method to create a new task
    public static Task createTask(int id, TaskName name) {
        return new Task(id, name, new ArrayList<>());
    }

    // Method to add a process to the task
    public void addProcess(Process process) {
        if (process != null) {
            this.processes.add(process);
            updateDurationAndCost();
        }
    }

    // Method to remove a process from the task
    public void removeProcess(Process process) {
        if (process != null && this.processes.remove(process)) {
            updateDurationAndCost();
        }
    }

//    Method to calculate the total cost of the task
    public double calculateCost() {
        double totalCost = 0.0;
        for (Process process : processes) {
            totalCost += process.getCost();
        }
        this.progressiveCost = totalCost;
        return totalCost;
    }

    // Method to calculate the total duration of the task
    public int calculateDuration() {
        int totalDuration = 0;
        for (Process process : processes) {
            totalDuration += process.getDuration();
        }
        this.duration = totalDuration;
        return totalDuration;
    }

    // Method to update the status of the task
//    public void updateStatus() {
//        this.status = processes.stream().allMatch(Process::isCompleted);
//    }

    // Helper method to update duration and cost
    private void updateDurationAndCost() {
        calculateCost();
        calculateDuration();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processes=" + processes +
                ", duration=" + duration +
                ", progressiveCost=" + progressiveCost +
                ", status=" + status +
                '}';
    }
}



package model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private List<Task> tasks;
    // Date of make
    
    private Status state;
    private double cost;
    private Duration duration;

    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED
    }

    public Project(int id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
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


    // public int projectsCount()



}
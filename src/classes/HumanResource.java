package classes;

public class HumanResource {
    private int id;
    private String name;
    private String jobTitle;
    private double hourlyRate;
    private int totalHoursWorked;

    public HumanResource(int id, String name, String jobTitle, double hourlyRate, int totalHoursWorked) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.hourlyRate = hourlyRate;
        this.totalHoursWorked = totalHoursWorked;
    }

    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

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

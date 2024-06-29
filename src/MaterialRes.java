

public class MaterialRes {
    private int id;
    private String name;
    private double costPerUnit;

    public MaterialRes(int id, String name, double costPerUnit) {
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

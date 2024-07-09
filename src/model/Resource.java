package model;

import java.util.List;

public interface Resource {
    int getId();
    String getName();
    double calculateCost(int units);
    static List<? extends Resource> getAllResources() {
        return List.of();  // Default implementation returns an empty list
    }


}
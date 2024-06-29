import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EquipmentResource equipment = new EquipmentResource(1, "Excavator", 1500.0);
        MaterialRes material = new MaterialRes(2, "Cement", 100.0);

        // Example of user input through a scanner (simulating a GUI input)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total hours worked by the human resource: ");
        int totalHoursWorked = scanner.nextInt();

        HumanResource human = new HumanResource(3, "John Doe", "Engineer", 50.0, totalHoursWorked);

        System.out.println("Equipment Cost for 10 units: " + equipment.calculateMaterialCost(10));
        System.out.println("Material Cost for 20 units: " + material.calculateMaterialCost(20));
        System.out.println("Human Resource ID: " + human.getId());
        System.out.println("Human Resource Hourly Rate: " + human.getHourlyRate());
        System.out.println("Human Resource Total Hours Worked: " + human.getTotalHoursWorked());

        Process process = new Process( 1 , "Construction") ;

        process.addResource(equipment);
        process.addResource(material);
        process.assignEmployee(human);

        System.out.println("Total Cost: " + process.calculateCost());
        System.out.println("Total Duration: " + process.calculateDuration());
        System.out.println("Process Status: " + process.getStatus());

        process.updateStatus("In Progress");
        System.out.println("Updated Process Status: " + process.getStatus());

        // Print the process details
        System.out.println(process);

        scanner.close();
    }
}

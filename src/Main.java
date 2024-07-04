import model.EquipmentResource;
import model.HumanResource;
import model.MaterialResource;
import model.Process;
import model.Task;
import model.Project;
import reader.*;
import ui.MainUI;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        // read material resource
        MRReader.MaterialResourceData data = MRReader.readMaterialResources("src\\data\\material-resource.txt");
        List<MaterialResource> materialResourceList = data.materialResourceList;
        Map<Integer, MaterialResource> materialResourceMap = data.materialResourceMap;
        //test
//        System.out.println("Number of materials: " + materialResourceList.size());
//        System.out.println("Material with ID 1: " + materialResourceMap.get(1).getName());

        // read equipment resource
        ERReader.EquipmentResourceData erData = ERReader.readEquipmentResources("src\\data\\equipment-resource.txt");
        List<EquipmentResource> equipmentResourceList = erData.equipmentResourceList;
        Map<Integer, EquipmentResource> equipmentResourceMap = erData.equipmentResourceMap;

        // test
//        System.out.println("\nEquipment Resources:");
//        System.out.println("Total number of equipment: " + equipmentResourceList.size());
//        System.out.println("First equipment: " + equipmentResourceMap.get(1).getName());
//        System.out.println("Last equipment: " + equipmentResourceMap.get(equipmentResourceList.size()).getName());


        HRReader.HumanResourceData hrData = HRReader.readHumanResources("src\\data\\human-resource.txt");
        List<HumanResource> humanResourceList = hrData.humanResourceList;
        Map<Integer, HumanResource> humanResourceMap = hrData.humanResourceMap;

        // test
//        System.out.println("\nAll Human Resources:");
//        for (HumanResource resource : humanResourceList) {
//            System.out.println(resource);
//        }


        // read process
        ProcessReader.ProcessData processData = ProcessReader.readProcesses("src\\data\\process.txt",
                humanResourceMap,
                materialResourceMap,
                equipmentResourceMap);
        List<Process> processList = processData.processList;
        Map<Integer, Process> processMap = processData.processMap;

        // test
//        System.out.println("\nAll Processes:");
//        for (Process process : processList) {
//            System.out.println(process.getMaterialResources().getFirst().getName());
//        }


        // Read Tasks
        TaskReader.TaskData taskData = TaskReader.readTasks("src\\data\\task.txt", processMap);
        List<Task> taskList = taskData.taskList;
        Map<Integer, Task> taskMap = taskData.taskMap;
        // Example of iterating through all tasks
//        System.out.println("\nAll Tasks:");
//        for (Task task : taskList) {
//            System.out.println(task.getProcesses());
//        }

        ProjectReader.ProjectData projectData = ProjectReader.readProjects("src\\data\\project.txt", taskMap);
        List<Project> projectList = projectData.projectList;
        Map<Integer, Project> projectMap = projectData.projectMap;
        // Example of iterating through all projects
//        System.out.println("\nAll Projects:");
//        for (Project p : projectList) {
//            System.out.println(p.getTasks());
//        }




        String[] uiArgs = {}; // You can pass arguments if needed
        MainUI.main(uiArgs);
        MainUI mainUI = new MainUI();



    }
}

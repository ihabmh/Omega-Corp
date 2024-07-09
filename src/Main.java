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
        MRReader.MaterialResourceData mrData = MRReader.readMaterialResources("src\\data\\material-resource.txt");
        List<MaterialResource> materialResourceList = mrData.materialResourceList;
        Map<Integer, MaterialResource> materialResourceMap = mrData.materialResourceMap;


        // read equipment resource
        ERReader.EquipmentResourceData erData = ERReader.readEquipmentResources("src\\data\\equipment-resource.txt");
        List<EquipmentResource> equipmentResourceList = erData.equipmentResourceList;
        Map<Integer, EquipmentResource> equipmentResourceMap = erData.equipmentResourceMap;



        HRReader.HumanResourceData hrData = HRReader.readHumanResources("src\\data\\human-resource.txt");
        List<HumanResource> humanResourceList = hrData.humanResourceList;
        Map<Integer, HumanResource> humanResourceMap = hrData.humanResourceMap;



        // read process
        ProcessReader.ProcessData processData = ProcessReader.readProcesses("src\\data\\process.txt",
                humanResourceMap,
                materialResourceMap,
                equipmentResourceMap);
        List<Process> processList = processData.processList;
        Map<Integer, Process> processMap = processData.processMap;



        // Read Tasks
        TaskReader.TaskData taskData = TaskReader.readTasks("src\\data\\task.txt", processMap);
        List<Task> taskList = taskData.taskList;
        Map<Integer, Task> taskMap = taskData.taskMap;


        ProjectReader.ProjectData projectData = ProjectReader.readProjects("src\\data\\project.txt", taskMap);
        List<Project> projectList = projectData.projectList;
        Map<Integer, Project> projectMap = projectData.projectMap;




        // intializing
        MaterialResource.initializeExistingMr(materialResourceList);
        EquipmentResource.initializeExistingEr(equipmentResourceList);
        HumanResource.initializeExistingHr(humanResourceList);
        Process.initializeExistingProcesses(processList);
        Task.initializeExistingTasks(taskList);
        Project.initializeExistingProjects(projectList);









        String[] uiArgs = {};
        MainUI.main(uiArgs);
        MainUI mainUI = new MainUI();



    }
}

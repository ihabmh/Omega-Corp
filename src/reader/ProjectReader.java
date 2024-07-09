package reader;

import model.*;
import model.Project;
import model.Process;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProjectReader {
    public static class ProjectData {
        public List<Project> projectList;
        public Map<Integer, Project> projectMap;

        public ProjectData(List<Project> list, Map<Integer, Project> map) {
            this.projectList = list;
            this.projectMap = map;
        }
    }

    public static ProjectData readProjects(String fileName, Map<Integer, Task> taskMap) {
        List<Project> projectList = new ArrayList<>();
        Map<Integer, Project> projectMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the first two lines (header and separator)
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    List<Task> tasks = parseTasks(parts[2], taskMap);

//                    System.out.println("Parsing project: ID=" + id + ", Name=" + name);
//                    System.out.println("Raw task string: " + parts[2]);
//                    System.out.println("Parsed tasks: " + tasks);

                    if (tasks.isEmpty()) {
                        tasks = null; // Set tasks to null if the list is empty
//                        System.out.println("Task list is empty, setting to null");
                    }

                    Project project = new Project(id, name, tasks);
                    projectList.add(project);
                    projectMap.put(id, project);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ProjectData(projectList, projectMap);
    }

    private static List<Task> parseTasks(String taskString, Map<Integer, Task> taskMap) {
        List<Task> tasks = Arrays.stream(taskString.replaceAll("[\\[\\]]", "").split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .map(taskMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

//        System.out.println("Parsed task IDs: " + tasks.stream().map(Task::getId).collect(Collectors.toList()));
        return tasks;
    }


}

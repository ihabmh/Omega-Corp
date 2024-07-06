package reader;

import model.*;
import model.Task;
import model.Process;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskReader {
    public static class TaskData {
        public List<Task> taskList;
        public Map<Integer, Task> taskMap;

        public TaskData(List<Task> list, Map<Integer, Task> map) {
            this.taskList = list;
            this.taskMap = map;
        }
    }

    public static TaskData readTasks(String fileName, Map<Integer, Process> processMap) {
        List<Task> taskList = new ArrayList<>();
        Map<Integer, Task> taskMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the first two lines (header and separator)
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    Task.TaskName name = Task.TaskName.valueOf(parts[1].trim());
                    List<Process> processes = parseProcesses(parts[2], processMap);

                    Task task = new Task(id, name, processes);
                    taskList.add(task);
                    taskMap.put(id, task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TaskData(taskList, taskMap);
    }

    private static List<Process> parseProcesses(String processString, Map<Integer, Process> processMap) {
        if (processString.trim().equals("[]") || processString.trim().equals("null")) {
            return new ArrayList<>(); // Return an empty list if processString is empty or "null"
        }

        return Arrays.stream(processString.replaceAll("[\\[\\]]", "").split(","))
                .map(s -> s.trim())
                .filter(s -> !s.isEmpty()) // Filter out empty strings
                .map(s -> Integer.parseInt(s))
                .map(processMap::get)
                .collect(Collectors.toList());
    }
}

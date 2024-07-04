package reader;

import model.*;
import model.Process;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessReader {
    public static class ProcessData {
        public List<Process> processList;
        public Map<Integer, Process> processMap;

        public ProcessData(List<Process> list, Map<Integer, Process> map) {
            this.processList = list;
            this.processMap = map;
        }
    }

    public static ProcessData readProcesses(String fileName,
                                            Map<Integer, HumanResource> humanResourceMap,
                                            Map<Integer, MaterialResource> materialResourceMap,
                                            Map<Integer, EquipmentResource> equipmentResourceMap) {
        List<Process> processList = new ArrayList<>();
        Map<Integer, Process> processMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the first two lines (header and separator)
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    List<HumanResource> humanResources = parseResources(parts[2], humanResourceMap);
                    List<MaterialResource> materialResources = parseResources(parts[3], materialResourceMap);
                    List<EquipmentResource> equipmentResources = parseResources(parts[4], equipmentResourceMap);
                    int duration = Integer.parseInt(parts[5].trim());
                    Process.Status status = parseStatus(parts[6].trim());
                    double cost = Double.parseDouble(parts[7].trim());

                    Process process = new Process(id, name, humanResources, materialResources, equipmentResources, duration, status, cost);
                    processList.add(process);
                    processMap.put(id, process);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ProcessData(processList, processMap);
    }

    private static <T> List<T> parseResources(String resourceString, Map<Integer, T> resourceMap) {
        return Arrays.stream(resourceString.replaceAll("[\\[\\]]", "").split(","))
                .map(s -> {
                    if (s.contains("/")) {
                        return Integer.parseInt(s.split("/")[0].trim());
                    }
                    return Integer.parseInt(s.trim());
                })
                .map(resourceMap::get)
                .collect(Collectors.toList());
    }

    private static Process.Status parseStatus(String status) {
        switch (status.toLowerCase()) {
            case "done":
                return Process.Status.COMPLETED;
            case "ongoing":
                return Process.Status.IN_PROGRESS;
            case "planned":
                return Process.Status.NOT_STARTED;
            default:
                return Process.Status.NOT_STARTED;
        }
    }
}
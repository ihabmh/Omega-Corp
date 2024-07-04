package reader;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRReader {
    public static class HumanResourceData {
        public List<HumanResource> humanResourceList;
        public Map<Integer, HumanResource> humanResourceMap;

        public HumanResourceData(List<HumanResource> list, Map<Integer, HumanResource> map) {
            this.humanResourceList = list;
            this.humanResourceMap = map;
        }
    }

    public static HumanResourceData readHumanResources(String fileName) {
        List<HumanResource> humanResourceList = new ArrayList<>();
        Map<Integer, HumanResource> humanResourceMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the first two lines (header and separator)
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    String jobTitle = parts[3].trim();
                    double hourlyRate = Double.parseDouble(parts[4].trim().replace("$", ""));

                    HumanResource resource = new HumanResource(id, name, jobTitle, hourlyRate);
                    humanResourceList.add(resource);
                    humanResourceMap.put(id, resource);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HumanResourceData(humanResourceList, humanResourceMap);
    }
}
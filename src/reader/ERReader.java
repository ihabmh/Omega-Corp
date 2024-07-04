package reader;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ERReader {
    public static class EquipmentResourceData {
        public List<EquipmentResource> equipmentResourceList;
        public Map<Integer, EquipmentResource> equipmentResourceMap;

        public EquipmentResourceData(List<EquipmentResource> list, Map<Integer, EquipmentResource> map) {
            this.equipmentResourceList = list;
            this.equipmentResourceMap = map;
        }
    }

    public static EquipmentResourceData readEquipmentResources(String fileName) {
        List<EquipmentResource> equipmentResourceList = new ArrayList<>();
        Map<Integer, EquipmentResource> equipmentResourceMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the first two lines (header and separator)
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    double costPerUnit = Double.parseDouble(parts[3].trim().replace("$", ""));

                    EquipmentResource resource = new EquipmentResource(id, name, costPerUnit);
                    equipmentResourceList.add(resource);
                    equipmentResourceMap.put(id, resource);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new EquipmentResourceData(equipmentResourceList, equipmentResourceMap);
    }
}
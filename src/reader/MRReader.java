package reader;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MRReader {
    public static class MaterialResourceData {
        public List<MaterialResource> materialResourceList;
        public Map<Integer, MaterialResource> materialResourceMap;

        public MaterialResourceData(List<MaterialResource> list, Map<Integer, MaterialResource> map) {
            this.materialResourceList = list;
            this.materialResourceMap = map;
        }
    }

    public static MaterialResourceData readMaterialResources(String fileName) {
        List<MaterialResource> materialResourceList = new ArrayList<>();
        Map<Integer, MaterialResource> materialResourceMap = new HashMap<>();

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

                    MaterialResource materialResource = new MaterialResource(id, name, costPerUnit);
                    materialResourceList.add(materialResource);
                    materialResourceMap.put(id, materialResource);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new MaterialResourceData(materialResourceList, materialResourceMap);
    }
}
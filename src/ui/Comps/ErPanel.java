package ui.Comps;


import javax.swing.*;
import java.awt.*;
import java.util.Map;

import model.Process;
import model.EquipmentResource;

public class ErPanel extends JPanel {

    public ErPanel(Process process) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        String[] columnNames = {"Equipment Name", "Count", "Cost"};

        Map<String, Long> equipmentCounts = process.getEquipmentResourceCountsByName();
        String[][] data = new String[equipmentCounts.size() + 1][3]; // +1 for the total row

        int index = 0;
        double totalCost = 0.0;

        // Populate data array based on equipmentCounts
        for (Map.Entry<String, Long> entry : equipmentCounts.entrySet()) {
            String equipmentName = entry.getKey();
            Long count = entry.getValue();

            // Find the corresponding EquipmentResource to get the cost
            EquipmentResource equipment = process.getEquipmentResources().stream()
                    .filter(e -> e.getName().equals(equipmentName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Equipment not found: " + equipmentName));

            // Assuming EquipmentResource has a method similar to calculateMaterialCost
            double cost = equipment.calculateEquipmentCost(count.intValue());
            totalCost += cost;


            data[index][0] = equipmentName;
            data[index][1] = count.toString();
            data[index][2] = String.format("%.2f", cost);
            index++;
        }

        // Add the total line in the table
        data[index][0] = "TOTAL";
        data[index][1] = "";
        data[index][2] = String.format("%.2f", totalCost);

        JLabel erLabel = new JLabel("Equipment Resource");
        erLabel.setFont(new Font("Arial", Font.BOLD, 16));


        JTable erTable = new JTable(data, columnNames);
        erTable.setBackground(Color.decode("#E6E5D9"));
        erTable.getTableHeader().setReorderingAllowed(false);
        for (int c = 0; c < erTable.getColumnCount(); c++) {
            Class<?> col_class = erTable.getColumnClass(c);
            erTable.setDefaultEditor(col_class, null);
        }
        add(erLabel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(erTable);
        add(scrollPane, BorderLayout.CENTER);





    }
}

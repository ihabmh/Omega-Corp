package ui.Comps;


import model.MaterialResource;
import model.Process;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MrPanel extends JPanel {

    public MrPanel(Process process) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));



        String[] columnNames = {"Resource Name", "Count", "Cost"};

        Map<String, Long> materialCounts = process.getMaterialResourceCountsByName();
        String[][] data = new String[materialCounts.size() + 1][3]; // +1 for the total row

        int index = 0;
        double totalCost = 0.0;

// Populate data array based on materialCounts
        for (Map.Entry<String, Long> entry : materialCounts.entrySet()) {
            String resourceName = entry.getKey();
            Long count = entry.getValue();

            // Find the corresponding MaterialResource to get the cost
            MaterialResource material = process.getMaterialResources().stream()
                    .filter(r -> r.getName().equals(resourceName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Resource not found: " + resourceName));

            double cost = material.calculateMaterialCost(count.intValue());
            totalCost += cost;

            data[index][0] = resourceName;
            data[index][1] = count.toString();
            data[index][2] = String.format("%.2f", cost);
            index++;
        }

// Add the total line in the table
        data[index][0] = "TOTAL";
        data[index][1] = "";
        data[index][2] = String.format("%.2f", totalCost);








        JLabel mrLabel = new JLabel("Material Resource");
        mrLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTable mrTable = new JTable(data, columnNames);
        mrTable.setBackground(Color.decode("#E6E5D9"));
        mrTable.getTableHeader().setReorderingAllowed(false);
        for (int c = 0; c < mrTable.getColumnCount(); c++) {
            Class<?> col_class = mrTable.getColumnClass(c);
            mrTable.setDefaultEditor(col_class, null);
        }
        add(mrLabel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(mrTable);
        add(scrollPane, BorderLayout.CENTER);





    }
}

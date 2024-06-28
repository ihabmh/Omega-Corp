package ui.Comps;


import javax.swing.*;
import java.awt.*;

public class ErPanel extends JPanel {

    public ErPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));



        String[] columnNames = {"ID", "Resource Name", "Count", "Cost"};
        String[][] data = {
                {"1", "Kundan Kumar Jha", "1", "1000ms"},
                {"2", "Anand Jha", "1", "1000ms"}
        };

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

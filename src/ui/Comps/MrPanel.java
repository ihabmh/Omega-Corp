package ui.Comps;


import javax.swing.*;
import java.awt.*;

public class MrPanel extends JPanel {

    public MrPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));



        String[] columnNames = {"ID", "model.Resource Name", "Count", "Cost"};
        String[][] data = {
                {"1", "Kundan Kumar Jha", "1", "1000ms"},
                {"2", "Anand Jha", "1", "1000ms"}
        };

        JLabel mrLabel = new JLabel("Equipment Resource");
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

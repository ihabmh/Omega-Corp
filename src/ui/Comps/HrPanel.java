package ui.Comps;


import javax.swing.*;
import java.awt.*;

public class HrPanel extends JPanel {

    public HrPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));



        String[] columnNames = {"ID", "Employee Name", "Job Title", "$/Hour"};
        String[][] data = {
                {"1", "Kundan Kumar Jha","Manager", "40$"},
                {"2", "Anand Jha","Engineer", "50$"}
        };

        JLabel hrLabel = new JLabel("Human Resource");
        hrLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTable hrTable = new JTable(data, columnNames);
        hrTable.setBackground(Color.decode("#E6E5D9"));
        hrTable.getTableHeader().setReorderingAllowed(false);
        for (int c = 0; c < hrTable.getColumnCount(); c++) {
            Class<?> col_class = hrTable.getColumnClass(c);
            hrTable.setDefaultEditor(col_class, null);
        }
        add(hrLabel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(hrTable);
        add(scrollPane, BorderLayout.CENTER);





    }
}

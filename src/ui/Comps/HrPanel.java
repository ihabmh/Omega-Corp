package ui.Comps;

import javax.swing.*;
import java.awt.*;
import model.Process;
import model.HumanResource;
import java.util.List;

public class HrPanel extends JPanel {

    public HrPanel(Process process) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        String[] columnNames = {"Employee Name", "Job Title", "$/Hour"};


        List<HumanResource> employees = process.getEmployees();


        Object[][] data = new Object[employees.size()][3];

        // Populate the data array
        for (int i = 0; i < employees.size(); i++) {
            HumanResource employee = employees.get(i);
            data[i][0] = employee.getName();
            data[i][1] = employee.getJobTitle();
            data[i][2] = employee.getHourlyRate();
        }

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
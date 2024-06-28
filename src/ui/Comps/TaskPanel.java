package ui.Comps;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TaskPanel extends JPanel {

    JLabel taskName;
    JPanel tablePanel;
    JTable table;
    JPanel action;
    JButton detailsBtn;
    JButton addProcess;

    public TaskPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());


        JLabel taskName = new JLabel("Task Name");
        taskName.setFont(new Font("Arial", Font.BOLD, 16));
        add(taskName, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Process Name", "Status", "Cost", "Duration"};
        String[][] data = {
                {"1", "Kundan Kumar Jha", "done", "100$", "1000ms"},
                {"2", "Anand Jha", "done", "500$", "1000ms"},

        };

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(600, 100));

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        String[] totalRow = {"TOTAL", "", "", "$600", "2000ms"};
        model.addRow(totalRow);
        JTable table = new JTable(model);
        table.setBackground(Color.decode("#E6E5D9"));
        table.getTableHeader().setReorderingAllowed(false);

        for (int c = 0; c < table.getColumnCount(); c++) {
            Class<?> col_class = table.getColumnClass(c);
            table.setDefaultEditor(col_class, null);
        }


        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);


        JPanel action = new JPanel();
        action.setBackground(Color.decode("#FFFEF1"));
        action.setLayout(new BoxLayout(action, BoxLayout.Y_AXIS));
        action.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        addProcess = new JButton("Add Process");
        addProcess.setBackground(Color.decode("#E0FF25"));
        addProcess.setForeground(Color.BLACK);
        addProcess.setBorderPainted(false);

        detailsBtn = new JButton("View Details");
        detailsBtn.setBackground(Color.BLACK);
        detailsBtn.setForeground(Color.WHITE);
        detailsBtn.setBorderPainted(false);


        action.add(Box.createVerticalGlue());
        action.add(detailsBtn);
        action.add(Box.createVerticalGlue());
        action.add(addProcess);
        action.add(Box.createVerticalGlue());
        add(action, BorderLayout.EAST);


    }
}

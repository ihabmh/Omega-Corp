package ui.Comps;

import model.Project;
import model.Task;
import model.Process;
import ui.AddProcessPanel;
import ui.ProcessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaskPanel extends JPanel {

    JLabel taskName;
    JPanel tablePanel;
    JTable table;
    JPanel action;
    JButton detailsBtn;
    JButton addProcess;

    public TaskPanel(JPanel panel, Project project, Task task) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());

        taskName = new JLabel(task.getName().toString());
        taskName.setFont(new Font("Arial", Font.BOLD, 16));
        add(taskName, BorderLayout.NORTH);

        String[] columnNames = {"Process Name", "Status", "Cost", "Duration"};

        // Get the processes from the task
        List<Process> processes = task.getProcesses();
        String[][] data = new String[processes.size() + 1][columnNames.length];

        int index = 0;
        double totalCost = 0;


        if (processes != null) {
            for (Process process : processes) {

                    data[index][0] = process.getName();
                    data[index][1] = process.getStatus().toString();
                    data[index][2] = "$" + process.getCost();
                    data[index][3] = process.getDuration() + "ms";
                    index++;

            }
        }


        // Add the total row
        data[index][0] = "TOTAL";
        data[index][1] = task.getStatus().toString();
        data[index][2] = "$" + task.getProgressiveCost();
        data[index][3] = task.getDuration() + "ms";

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(600, 100));

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setBackground(Color.decode("#E6E5D9"));
        table.getTableHeader().setReorderingAllowed(false);

        for (int c = 0; c < table.getColumnCount(); c++) {
            Class<?> col_class = table.getColumnClass(c);
            table.setDefaultEditor(col_class, null);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        action = new JPanel();
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

        detailsBtn.addActionListener(e -> {

            int processIndex = table.getSelectedRow();
            if (processIndex >= 0 && processIndex < processes.size()) {
                Process process = processes.get(processIndex);
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                ProcessPanel processPanel = new ProcessPanel(panel, process);
                panel.add(processPanel, "processPanel");
                cardLayout.show(panel, "processPanel");
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a process");
            }



        });

        addProcess.addActionListener(e -> {
            AddProcessPanel app = new AddProcessPanel(panel, project, task);
            panel.add(app, "addProcessPanel");
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "addProcessPanel");
        });
    }
}

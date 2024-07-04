package ui.Comps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterPanel extends JPanel {

    private JComboBox<String> projectComboBox;
    private JComboBox<String> taskComboBox;
    private JComboBox<String> processComboBox;
    private JButton searchButton;

    public FilterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(new Dimension(300, 150));
        setBackground(Color.decode("#FFFEF1"));

        // Initialize combo boxes
        projectComboBox = new JComboBox<>(new String[]{"Project", "Project A", "Project B"});
        taskComboBox = new JComboBox<>(new String[]{"Task"});
        processComboBox = new JComboBox<>(new String[]{"Process"});
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorderPainted(false);

        // Disable task and process combo boxes initially
        taskComboBox.setEnabled(false);
        processComboBox.setEnabled(false);

        // Add action listeners
        projectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectComboBox.getSelectedIndex() > 0) {
                    taskComboBox.setEnabled(true);
                    // Update taskComboBox items based on selected project
                    taskComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Task", "Task 1", "Task 2"}));
                } else {
                    taskComboBox.setEnabled(false);
                    processComboBox.setEnabled(false);
                }
            }
        });

        taskComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskComboBox.getSelectedIndex() > 0) {
                    processComboBox.setEnabled(true);
                    // Update processComboBox items based on selected task
                    processComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select Process", "Process X", "Process Y"}));
                } else {
                    processComboBox.setEnabled(false);
                }
            }
        });

        // Add components to panel
        add(projectComboBox);
        add(Box.createHorizontalStrut(5));
        add(taskComboBox);
        add(Box.createHorizontalStrut(5));
        add(processComboBox);
        add(Box.createHorizontalStrut(5));
        add(searchButton);

        // Add search button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectComboBox.getSelectedIndex() > 0 && taskComboBox.getSelectedIndex() > 0 && processComboBox.getSelectedIndex() > 0) {
                    String selectedProject = (String) projectComboBox.getSelectedItem();
                    String selectedTask = (String) taskComboBox.getSelectedItem();
                    String selectedProcess = (String) processComboBox.getSelectedItem();
                    // Implement search functionality here
                    JOptionPane.showMessageDialog(FilterPanel.this, "Searching for " + selectedProject + " - " + selectedTask + " - " + selectedProcess);
                } else {
                    JOptionPane.showMessageDialog(FilterPanel.this, "Please make a selection in all fields.");
                }
            }
        });
    }
}

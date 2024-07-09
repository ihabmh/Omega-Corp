package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



import model.Process;
import model.*;
import ui.Comps.TaskPanel;

public class FilterPanel extends JPanel {

    private JComboBox<NamedItem<Project>> projectComboBox;
    private JComboBox<NamedItem<Task>> taskComboBox;
    private JComboBox<NamedItem<Process>> processComboBox;
    private JButton searchButton;

    public FilterPanel(JPanel panel) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(new Dimension(300, 150));
        setBackground(Color.decode("#FFFEF1"));

        // Initialize combo boxes
        List<Project> projects = Project.getAllProjects();
        projectComboBox = new JComboBox<>(new DefaultComboBoxModel<>(
                projects.stream().map(p -> new NamedItem<>(p, p.getName())).toArray(NamedItem[]::new)
        ));
        projectComboBox.insertItemAt(new NamedItem<>(null, "Select Project"), 0);
        projectComboBox.setSelectedIndex(0);

        taskComboBox = new JComboBox<>();
        taskComboBox.insertItemAt(new NamedItem<>(null, "Select Task"), 0);
        taskComboBox.setSelectedIndex(0);

        processComboBox = new JComboBox<>();
        processComboBox.insertItemAt(new NamedItem<>(null, "Select Process"), 0);
        processComboBox.setSelectedIndex(0);

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
                NamedItem<Project> selectedItem = (NamedItem<Project>) projectComboBox.getSelectedItem();
                Project selectedProject = selectedItem != null ? selectedItem.getItem() : null;
                if (selectedProject != null) {
                    List<Task> tasks = selectedProject.getTasks();
                    taskComboBox.setModel(new DefaultComboBoxModel<>(
                            tasks.stream().map(t -> new NamedItem<>(t, t.getName().toString())).toArray(NamedItem[]::new)
                    ));
                    taskComboBox.insertItemAt(new NamedItem<>(null, "Select Task"), 0);
                    taskComboBox.setSelectedIndex(0);
                    taskComboBox.setEnabled(true);
                } else {
                    taskComboBox.setModel(new DefaultComboBoxModel<>());
                    taskComboBox.setEnabled(false);
                }
                processComboBox.setModel(new DefaultComboBoxModel<>());
                processComboBox.setEnabled(false);
            }
        });

        taskComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NamedItem<Task> selectedItem = (NamedItem<Task>) taskComboBox.getSelectedItem();
                Task selectedTask = selectedItem != null ? selectedItem.getItem() : null;
                if (selectedTask != null) {
                    List<Process> processes = selectedTask.getProcesses();
                    processComboBox.setModel(new DefaultComboBoxModel<>(
                            processes.stream().map(p -> new NamedItem<>(p, p.getName())).toArray(NamedItem[]::new)
                    ));
                    processComboBox.insertItemAt(new NamedItem<>(null, "Select Process"), 0);
                    processComboBox.setSelectedIndex(0);
                    processComboBox.setEnabled(true);
                } else {
                    processComboBox.setModel(new DefaultComboBoxModel<>());
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
                NamedItem<Project> selectedProjectItem = (NamedItem<Project>) projectComboBox.getSelectedItem();
                NamedItem<Task> selectedTaskItem = (NamedItem<Task>) taskComboBox.getSelectedItem();
                NamedItem<Process> selectedProcessItem = (NamedItem<Process>) processComboBox.getSelectedItem();

                Project selectedProject = selectedProjectItem != null ? selectedProjectItem.getItem() : null;
                Task selectedTask = selectedTaskItem != null ? selectedTaskItem.getItem() : null;
                Process selectedProcess = selectedProcessItem != null ? selectedProcessItem.getItem() : null;

                CardLayout cardLayout = (CardLayout) panel.getLayout();

                if (selectedProject != null) {
                    if (selectedTask != null) {
                        if (selectedProcess != null) {
                            // Process is selected
                            ProcessPanel processPanel = new ProcessPanel(panel, selectedProcess);
                            panel.add(processPanel, "searchedProcess");
                            cardLayout.show(panel, "searchedProcess");
                        } else {
                            // Task is selected
                            TaskPanel taskPanel = new TaskPanel(panel, selectedProject, selectedTask);
                            panel.add(taskPanel, "searchedTask");
                            cardLayout.show(panel, "searchedTask");
                        }
                    } else {
                        // Only Project is selected
                        ProjectPanel projectPanel = new ProjectPanel(panel, selectedProject);
                        panel.add(projectPanel, "searchedProject");
                        cardLayout.show(panel, "searchedProject");
                    }
                } else {
                    JOptionPane.showMessageDialog(FilterPanel.this, "Please select at least a project.");
                }
            }
        });
    }

    private static class NamedItem<T> {
        private final T item;
        private final String name;

        public NamedItem(T item, String name) {
            this.item = item;
            this.name = name;
        }

        public T getItem() {
            return item;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
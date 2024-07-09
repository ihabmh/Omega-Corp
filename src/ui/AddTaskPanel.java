package ui;

import model.Project;
import model.Task;

import javax.swing.*;
import java.awt.*;



public class AddTaskPanel extends JPanel {
    JPanel headerPanel;
    JPanel btnsPanel;
    JLabel contentTitle;
    JButton addBtn;
    JButton cancelBtn;
    JPanel contentPanel;
    JPanel  namePanel;
    JLabel nameLabel;
    private JComboBox<Task.TaskName> nameComboBox;

    public AddTaskPanel(JPanel panel, Project project) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.decode("#FFFEF1"));

        contentTitle = new JLabel(project.getName(), SwingConstants.LEFT);
        contentTitle.setFont( new Font("Arial", Font.BOLD, 20));


        addBtn = new JButton("Add");
        addBtn.setBackground(Color.BLACK);
        addBtn.setForeground(Color.WHITE);
        addBtn.setBorderPainted(false);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(Color.decode("#E0FF25"));
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.setBorderPainted(false);

        btnsPanel.add(cancelBtn);
        btnsPanel.add(addBtn);

        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(btnsPanel, BorderLayout.EAST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#FFFEF1"));


        namePanel = new JPanel();
        namePanel.setBackground(Color.decode("#FFFEF1"));
        namePanel.setLayout(new GridLayout(0,1));

        nameLabel = new JLabel("Choose a Task:", SwingConstants.LEFT);
        nameComboBox = new JComboBox<>(Task.TaskName.values());
        nameComboBox.setBackground(Color.decode("#E6E5D9"));
        nameComboBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        namePanel.add(nameLabel);
        namePanel.add(nameComboBox);

        contentPanel.add(namePanel, BorderLayout.NORTH);


        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);



        cancelBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();

            cardLayout.show(panel, "projectPanel");
        });

        addBtn.addActionListener(e -> {
            Task.TaskName selectedTaskName = (Task.TaskName) nameComboBox.getSelectedItem();

            // Check if task name is not selected
            if (selectedTaskName == null) {
                JOptionPane.showMessageDialog(null, "Please choose a task name.", "Empty Name", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if task name is not selected
            }

            // Check if the task already exists
            boolean taskExists = false;
            if(project.getTasks() != null){
                taskExists = project.getTasks().stream()
                        .anyMatch(task -> task.getName().equals(selectedTaskName));
            }

            if (taskExists) {
                JOptionPane.showMessageDialog(null, "This task already exists. Please choose a different task name.", "Duplicate Task", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the task already exists
            }

            int confirmation = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to add a task named \"" + selectedTaskName + "\"?",
                    "Confirm Task Add", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                Task newTask = Task.createTask(selectedTaskName);
                project.addTask(newTask);

                System.out.println(project.getTasks());
                Project.updateProjectFileWithNewTask(project.getId(), newTask.getId());
                System.out.println(project);



                nameComboBox.setSelectedIndex(-1);

                CardLayout cardLayout = (CardLayout) panel.getLayout();
                panel.add(new ProjectPanel(panel, project), "projectPanel");
                cardLayout.show(panel, "projectPanel");
            }
        });


    }


}

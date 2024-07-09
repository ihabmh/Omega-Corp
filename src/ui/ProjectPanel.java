package ui;

import ui.Comps.TaskPanel;
import model.Task;
import model.Project;

import javax.swing.*;
import java.awt.*;

public class ProjectPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel btnsPanel;
    private JLabel contentTitle;
    private JButton addTaskBtn;
    private JButton backBtn;
    private JPanel contentPanel;
    private JPanel statusPanel;

    public ProjectPanel(JPanel panel, Project project) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.decode("#FFFEF1"));

        contentTitle = new JLabel(project.getName(), SwingConstants.LEFT);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));

        addTaskBtn = new JButton("+ Task");
        addTaskBtn.setBackground(Color.BLACK);
        addTaskBtn.setForeground(Color.WHITE);
        addTaskBtn.setBorderPainted(false);

        backBtn = new JButton("Back");
        backBtn.setBackground(Color.decode("#E0FF25"));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBorderPainted(false);

        btnsPanel.add(backBtn);
        btnsPanel.add(addTaskBtn);

        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(btnsPanel, BorderLayout.EAST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#FFFEF1"));


        if (project.getTasks() !=null){
            for(Task task : project.getTasks()){
                contentPanel.add(new TaskPanel(panel, project, task));
                contentPanel.add(Box.createVerticalStrut(20));
            }
        }





        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportBorder(null);

        statusPanel = new JPanel();
        statusPanel.setBackground(Color.decode("#E0FF25"));
        statusPanel.setPreferredSize(new Dimension(100, 50));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        JLabel statusLabel = new JLabel("Project Status: " + project.getStatus()); // add status on label
        JLabel durationLavel = new JLabel("Project duration: " + project.getDuration() ); // add duration on label
        JLabel costLavel = new JLabel("Project cost: " + project.getCost() );
        JButton exctBtn = new JButton("Excute");
        exctBtn.setBackground(Color.BLACK);
        exctBtn.setForeground(Color.WHITE);
        exctBtn.setBorderPainted(false);

        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(durationLavel);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(costLavel);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(exctBtn);


        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            panel.add(new DashboardPanel(panel), "dashboardPanel");
            cardLayout.show(panel, "dashboardPanel");
        });

        addTaskBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            panel.add(new AddTaskPanel(panel, project), "addTaskPanel");
            cardLayout.show(panel, "addTaskPanel");
        });

        exctBtn.addActionListener(e -> {
            // Create a Timer to periodically refresh the ProjectPanel
            Timer refreshTimer = new Timer(1000, event -> {
                // Refresh the ProjectPanel to show live results
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                panel.add(new ProjectPanel(panel, project), "projectPanel");
                cardLayout.show(panel, "projectPanel");
            });

            // Create a SwingWorker to run project.execute() on a background thread
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    project.execute();  // Execute the project
                    return null;
                }

                @Override
                protected void done() {
                    // Stop the refreshTimer and show the final ProjectPanel after execute is finished
                    refreshTimer.stop();
                    CardLayout cardLayout = (CardLayout) panel.getLayout();
                    panel.add(new ProjectPanel(panel, project), "projectPanel");
                    cardLayout.show(panel, "projectPanel");
                }
            };

            // Start the refreshTimer to update the ProjectPanel every 1 second
            refreshTimer.start();

            // Schedule the refreshTimer to stop after project.getDuration() + 1000 milliseconds
            Timer stopTimer = new Timer((int)project.getDuration() + 1000, e1 -> refreshTimer.stop());
            stopTimer.setRepeats(false); // Ensure this timer only executes once
            stopTimer.start();

            // Start the SwingWorker
            worker.execute();

            // Log the duration for debugging purposes
            System.out.println("Project duration: " + project.getDuration());
        });


    }
}

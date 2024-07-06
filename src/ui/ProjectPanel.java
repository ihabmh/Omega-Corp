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
                contentPanel.add(new TaskPanel(panel, task));
                contentPanel.add(Box.createVerticalStrut(20));
            }
        }




        // Wrap contentPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportBorder(null);

        statusPanel = new JPanel();
        statusPanel.setBackground(Color.decode("#E0FF25"));
        statusPanel.setPreferredSize(new Dimension(100, 50));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        JLabel statusLabel = new JLabel("Project Status:"); // add status on label
        JLabel durationLavel = new JLabel("Project duration:"); // add duration on label
        JLabel costLavel = new JLabel("Project cost:"); // add total cost on label
        JButton exctBtn = new JButton("Excute");
        exctBtn.setBackground(Color.BLACK);
        exctBtn.setForeground(Color.WHITE);
        exctBtn.setBorderPainted(false);

        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalStrut(150));
        statusPanel.add(durationLavel);
        statusPanel.add(Box.createHorizontalStrut(150));
        statusPanel.add(costLavel);
        statusPanel.add(Box.createHorizontalStrut(150));
        statusPanel.add(exctBtn);


        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "dashboardPanel");
        });
    }
}

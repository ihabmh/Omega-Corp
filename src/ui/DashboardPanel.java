package ui;

import ui.Comps.ProjectCardPanel;
import ui.ProjectCreationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {
    private JButton createProjectButton;

    public DashboardPanel(JPanel panel) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel contentTitle = new JLabel("Dashboard", SwingConstants.LEFT);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));

        createProjectButton = new JButton("Create Project");
        createProjectButton.setBackground(Color.BLACK);
        createProjectButton.setForeground(Color.WHITE);

        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(createProjectButton, BorderLayout.EAST);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode("#FFFEF1"));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 5; i++) {
            contentPanel.add(new ProjectCardPanel(panel));
            contentPanel.add(Box.createVerticalStrut(20));
        }

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);



        createProjectButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "projectCreationPanel");
        });


    }



}


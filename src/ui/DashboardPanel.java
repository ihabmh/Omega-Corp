package ui;

import ui.Comps.ProjectCardPanel;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel contentTitle = new JLabel("Dashboard", SwingConstants.LEFT);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JButton createProjectButtonTitle = new JButton("Create Project");
        createProjectButtonTitle.setBackground(Color.BLACK);
        createProjectButtonTitle.setForeground(Color.WHITE);

        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(createProjectButtonTitle, BorderLayout.EAST);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode("#FFFEF1"));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 5; i++) {
            contentPanel.add(new ProjectCardPanel());
            contentPanel.add(Box.createVerticalStrut(20));
        }

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
}

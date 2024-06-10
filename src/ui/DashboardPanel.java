package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add 10 pixels margin below

        JLabel contentTitle = new JLabel("Dashboard", SwingConstants.LEFT);
        Font currentFont = contentTitle.getFont();
        contentTitle.setFont(currentFont.deriveFont(Font.BOLD));

        JButton createProjectButtonTitle = new JButton("Create Project");
        createProjectButtonTitle.setBackground(Color.BLACK);
        createProjectButtonTitle.setForeground(Color.WHITE);

        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(createProjectButtonTitle, BorderLayout.EAST);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode("#FFFEF1"));
        contentPanel.setLayout(new GridLayout(5, 1, 20, 20));

        for (int i = 0; i < 4; i++) {
            JPanel card = new JPanel();
            card.setBackground(new Color(230, 230, 220));
            contentPanel.add(card);
        }

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
}

package ui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {


    public AdminPanel(JPanel panel) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel contentTitle = new JLabel("Admin", SwingConstants.LEFT);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));

        headerPanel.add(contentTitle, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode("#FFFEF1"));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));





        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

    }
}

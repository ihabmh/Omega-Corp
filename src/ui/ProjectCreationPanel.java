package ui;

import javax.swing.*;
import java.awt.*;

import model.Project;

public class ProjectCreationPanel extends JPanel {
    JPanel headerPanel;
    JPanel btnsPanel;
    JLabel contentTitle;
    JButton createBtn;
    JButton cancelBtn;
    JPanel contentPanel;
    JPanel  namePanel;
    JLabel nameLabel;
    JTextField nameTF;

    public ProjectCreationPanel(JPanel panel) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.decode("#FFFEF1"));

        contentTitle = new JLabel("Project Creation", SwingConstants.LEFT);
        contentTitle.setFont( new Font("Arial", Font.BOLD, 20));


        createBtn = new JButton("Create");
        createBtn.setBackground(Color.BLACK);
        createBtn.setForeground(Color.WHITE);
        createBtn.setBorderPainted(false);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(Color.decode("#E0FF25"));
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.setBorderPainted(false);

        btnsPanel.add(cancelBtn);
        btnsPanel.add(createBtn);

        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(btnsPanel, BorderLayout.EAST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#FFFEF1"));


        namePanel = new JPanel();
        namePanel.setBackground(Color.decode("#FFFEF1"));
        namePanel.setLayout(new GridLayout(0,1));

        nameLabel = new JLabel("Choose a Name:", SwingConstants.LEFT);
        nameTF = new JTextField(20);
        nameTF.setBackground(Color.decode("#E6E5D9"));
        nameTF.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        namePanel.add(nameLabel);
        namePanel.add(nameTF);

        contentPanel.add(namePanel, BorderLayout.NORTH);


        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);



        cancelBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();

            cardLayout.show(panel, "dashboardPanel");
        });

        createBtn.addActionListener(e -> {

            String name = nameTF.getText().trim(); // Trim whitespaces

            // Check if title is empty
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a project name.", "Empty Name", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if title is empty
            }


            int confirmation = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to create a project named \"" + name + "\"?",
                    "Confirm Project Creation", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // id should respect the order
                Project newProject = Project.createProject(name);
                nameTF.setText("");


                CardLayout cardLayout = (CardLayout) panel.getLayout();
                DashboardPanel dp = new DashboardPanel(panel);
                panel.add(dp, "dashboardPanel");
                cardLayout.show(panel, "dashboardPanel");


            }
        });
    }


}

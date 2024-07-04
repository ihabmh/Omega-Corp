package ui;

import ui.Comps.FilterPanel;

import javax.swing.*;
import java.awt.*;


public class MainUI extends JFrame {

    AddProcessPanel addProcessPanel;
    ProcessPanel processPanel;
    ProjectCreationPanel projectCreationPanel;
    ProjectPanel projectPanel;
    DashboardPanel dashboardPanel;



    public MainUI() {
        setTitle("Omega");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src\\img\\icon.png");
        setIconImage(icon.getImage());

        // Sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.decode("#E1FF25"));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(230, getHeight()));
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
            logo.setIcon(logoIcon);
        } catch (Exception e) {
            logo.setText("\u03A9");
            logo.setFont(new Font("Serif", Font.BOLD, 36));
        }
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding around the logo
        sidebar.add(logo);

        // Add sidebar buttons
        sidebar.add(createSidebarButton("Dashboard", true));
        sidebar.add(createSidebarButton("Admin", false));

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#FFFEF1"));

        // Top panel with search bar and create project button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#FFFEF1"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//        JTextField searchField = new JTextField();
//        searchField.setPreferredSize(new Dimension(300, 30));
        topPanel.add(new FilterPanel(), BorderLayout.CENTER);

        JButton createProjectButton = new JButton("Project");
        createProjectButton.setBackground(Color.BLACK);
        createProjectButton.setForeground(Color.WHITE);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Change Panels


        JPanel shownPanel = new JPanel();
        shownPanel.setLayout(new CardLayout());



        addProcessPanel = new AddProcessPanel(shownPanel);
        processPanel = new ProcessPanel(shownPanel);
        projectCreationPanel = new ProjectCreationPanel(shownPanel);
        projectPanel = new ProjectPanel(shownPanel, 1);
        dashboardPanel = new DashboardPanel(shownPanel);


        shownPanel.add(addProcessPanel, "addProcessPanel");
        shownPanel.add(processPanel, "processPanel");
        shownPanel.add(projectPanel, "projectPanel");
        shownPanel.add(dashboardPanel, "dashboardPanel");
        shownPanel.add(projectCreationPanel, "projectCreationPanel");

        CardLayout cardLayout = (CardLayout) shownPanel.getLayout();
        cardLayout.show(shownPanel, "dashboardPanel");

        mainPanel.add(shownPanel, BorderLayout.CENTER);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

    }

    private JButton createSidebarButton(String text, boolean selected) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(selected ? Color.decode("#F9FFD3") : Color.decode("#E1FF25"));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(10, 20, 10, 20));
        button.setFont(button.getFont().deriveFont(Font.BOLD, 12));
        return button;
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI frame = new MainUI();
            frame.setVisible(true);
        });
    }
}

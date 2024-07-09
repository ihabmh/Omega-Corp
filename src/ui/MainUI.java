package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {

    private JButton dashboardButton;
    private JButton adminButton;

    public MainUI() {
        setTitle("Omega");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/img/icon.png");
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

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#FFFEF1"));

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#FFFEF1"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton createProjectButton = new JButton("Project");
        createProjectButton.setBackground(Color.BLACK);
        createProjectButton.setForeground(Color.WHITE);


        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Card Layout panel
        JPanel shownPanel = new JPanel();
        shownPanel.setLayout(new CardLayout());

        shownPanel.add(new DashboardPanel(shownPanel), "dashboardPanel");
        shownPanel.add(new ProjectCreationPanel(shownPanel), "projectCreationPanel");
        shownPanel.add(new AdminPanel(shownPanel), "adminPanel");

        CardLayout cardLayout = (CardLayout) shownPanel.getLayout();
        cardLayout.show(shownPanel, "DashboardPanel");

        mainPanel.add(shownPanel, BorderLayout.CENTER);
        topPanel.add(new FilterPanel(shownPanel), BorderLayout.CENTER);

        // Create sidebar buttons
        dashboardButton = createSidebarButton("Dashboard", true, e -> showDashboard(shownPanel));
        adminButton = createSidebarButton("Admin", false, e -> showAdmin(shownPanel));

        // Add buttons to sidebar
        sidebar.add(dashboardButton);
        sidebar.add(adminButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createSidebarButton(String text, boolean selected, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(selected ? Color.decode("#F9FFD3") : Color.decode("#E1FF25"));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(10, 20, 10, 20));
        button.setFont(button.getFont().deriveFont(Font.BOLD, 12));
        button.addActionListener(actionListener);  // Add the ActionListener
        return button;
    }

    private void showDashboard(JPanel panel) {
        CardLayout cardLayout = (CardLayout) panel.getLayout();
        cardLayout.show(panel, "dashboardPanel");
        updateButtonSelection(dashboardButton, adminButton);
    }

    private void showAdmin(JPanel panel) {
        CardLayout cardLayout = (CardLayout) panel.getLayout();
        cardLayout.show(panel, "adminPanel");
        updateButtonSelection(adminButton, dashboardButton);
    }

    private void updateButtonSelection(JButton selectedButton, JButton... otherButtons) {
        setButtonSelected(selectedButton, true);
        for (JButton button : otherButtons) {
            setButtonSelected(button, false);
        }
    }

    private void setButtonSelected(JButton button, boolean selected) {
        button.setBackground(selected ? Color.decode("#F9FFD3") : Color.decode("#E1FF25"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI frame = new MainUI();
            frame.setVisible(true);
        });
    }
}

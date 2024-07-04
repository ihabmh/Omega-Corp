package ui;

import ui.Comps.*;

import javax.swing.*;
import java.awt.*;

public class AddProcessPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel btnsPanel;
    private JLabel contentTitle;
    private JButton backBtn;
    private JButton createBtn;
    private JPanel contentPanel;
    private ListPanel humanResourcesListPanel;
    private ListPanel materialResourcesListPanel;
    private ListPanel equipmentResourcesListPanel;

    public AddProcessPanel(JPanel panel) {
        // Initialize the panel settings
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Initialize header panel and its components
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#FFFEF1"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.decode("#FFFEF1"));

        contentTitle = new JLabel("Task Name", SwingConstants.LEFT);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));

        backBtn = new JButton("Back");
        backBtn.setBackground(Color.decode("#E0FF25"));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBorderPainted(false);

        createBtn = new JButton("Create");
        createBtn.setBackground(Color.BLACK);
        createBtn.setForeground(Color.WHITE);
        createBtn.setBorderPainted(false);

        // Add components to btnsPanel and headerPanel
        btnsPanel.add(backBtn);
        btnsPanel.add(createBtn);
        headerPanel.add(contentTitle, BorderLayout.WEST);
        headerPanel.add(btnsPanel, BorderLayout.EAST);

        // Initialize content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#FFFEF1"));

        // Form panel setup
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));
        form.setBackground(Color.decode("#FFFEF1"));

        // Name panel setup
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setSize(new Dimension(400, 50));
        namePanel.setBackground(Color.decode("#FFFEF1"));
        namePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel name = new JLabel("Enter a name:", SwingConstants.LEFT);
        JTextField nameField = new JTextField(10);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));  // Set max height to 30 pixels

        namePanel.add(name);
        namePanel.add(nameField);

        // Duration panel setup
        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new BoxLayout(durationPanel, BoxLayout.Y_AXIS));
        durationPanel.setBackground(Color.decode("#FFFEF1"));
        durationPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel duration = new JLabel("Enter a duration(ms):", SwingConstants.LEFT);
        JTextField durationField = new JTextField(10);
        durationField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));  // Set max height to 30 pixels

        durationPanel.add(duration);
        durationPanel.add(durationField);

        // Initialize list panels
        humanResourcesListPanel = new ListPanel("Human Resources List");
        materialResourcesListPanel = new ListPanel("Material Resources List");
        equipmentResourcesListPanel = new ListPanel("Equipment Resources List");

        // Initialize resource panels
        ResourcePanel humanResourcesPanel = new ResourcePanel("Human Resources:",
                new String[]{"Choose", "Department A", "Department B", "Manager"},
                humanResourcesListPanel.getListModel());

        ResourcePanel materialResourcesPanel = new ResourcePanel("Material Resources:",
                new String[]{"Choose", "Material A", "Material B", "Material C"},
                materialResourcesListPanel.getListModel());

        ResourcePanel equipmentResourcesPanel = new ResourcePanel("Equipment Resources:",
                new String[]{"Choose", "Equipment A", "Equipment B", "Equipment C"},
                equipmentResourcesListPanel.getListModel());

        // Add components to form
        form.add(namePanel);
        form.add(durationPanel);
        form.add(humanResourcesPanel);
        form.add(materialResourcesPanel);
        form.add(equipmentResourcesPanel);
        form.add(Box.createVerticalGlue());

        // Add form to content panel
        contentPanel.add(form);

        // Panel to hold the lists
        JPanel listsPanel = new JPanel();
        listsPanel.setLayout(new GridLayout(3, 1));
        listsPanel.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));
        listsPanel.setBackground(Color.decode("#FFFEF1"));

        // Add list panels to listsPanel
        listsPanel.add(humanResourcesListPanel);
        listsPanel.add(materialResourcesListPanel);
        listsPanel.add(equipmentResourcesListPanel);

        // Add header, content, and lists panels to the main panel
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.WEST);
        add(listsPanel, BorderLayout.EAST);

        backBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "projectPanel");
        });
    }
}

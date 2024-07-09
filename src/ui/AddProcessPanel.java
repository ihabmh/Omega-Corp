package ui;

import model.*;
import model.Process;
import ui.Comps.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddProcessPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel btnsPanel;
    private JLabel contentTitle;
    private JButton backBtn;
    private JButton addBtn;
    private JPanel contentPanel;
    private HumanListPanel humanResourcesListPanel;
    private MaterialListPanel materialResourcesListPanel;
    private EquipmentListPanel equipmentResourcesListPanel;
    private JTextField nameField;
    private JTextField durationField;

    public AddProcessPanel(JPanel panel, Project project, Task task) {
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

        contentTitle = new JLabel(task.getName().toString(), SwingConstants.LEFT);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));

        backBtn = new JButton("Back");
        backBtn.setBackground(Color.decode("#E0FF25"));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBorderPainted(false);

        addBtn = new JButton("Add Process");
        addBtn.setBackground(Color.BLACK);
        addBtn.setForeground(Color.WHITE);
        addBtn.setBorderPainted(false);

        // Add components to btnsPanel and headerPanel
        btnsPanel.add(backBtn);
        btnsPanel.add(addBtn);
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
        nameField = new JTextField(10);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));  // Set max height to 30 pixels

        namePanel.add(name);
        namePanel.add(nameField);

        // Duration panel setup
        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new BoxLayout(durationPanel, BoxLayout.Y_AXIS));
        durationPanel.setBackground(Color.decode("#FFFEF1"));
        durationPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel duration = new JLabel("Enter a duration (ms):", SwingConstants.LEFT);
        durationField = new JTextField(10);
        durationField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));  // Set max height to 30 pixels

        durationPanel.add(duration);
        durationPanel.add(durationField);

        // Initialize list panels
        humanResourcesListPanel = new HumanListPanel("Human Resources List");
        materialResourcesListPanel = new MaterialListPanel("Material Resources List");
        equipmentResourcesListPanel = new EquipmentListPanel("Equipment Resources List");

        // Initialize resource panels
        MaterialResourcePanel materialResourcesPanel = new MaterialResourcePanel("Material Resources:",
                MaterialResource.getAllMaterialResources(),
                materialResourcesListPanel.getListModel());

        HumanResourcePanel humanResourcesPanel = new HumanResourcePanel("Human Resources:",
                HumanResource.getAllHumanResources(),
                humanResourcesListPanel.getListModel());

        EquipmentResourcePanel equipmentResourcesPanel = new EquipmentResourcePanel("Equipment Resources:",
                EquipmentResource.getAllEquipmentResources(),
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
        add(contentPanel, BorderLayout.CENTER);  // Changed to CENTER for proper layout
        add(listsPanel, BorderLayout.EAST);

        backBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "projectPanel");
        });

        addBtn.addActionListener(e -> {
            double processDuration;
            String processName = nameField.getText().trim();
            try {
                processDuration = Double.parseDouble(durationField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid duration. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<MaterialResource> selectedMaterialResources = getSelectedResources(materialResourcesListPanel.getListModel(), MaterialResource.class);
            List<EquipmentResource> selectedEquipmentResources = getSelectedResources(equipmentResourcesListPanel.getListModel(), EquipmentResource.class);
            List<HumanResource> selectedHumanResources = getSelectedResources(humanResourcesListPanel.getListModel(), HumanResource.class);

            // Create a new Process object and add it to the task
            Process newProcess = Process.createProcess(processName, processDuration, selectedHumanResources, selectedMaterialResources, selectedEquipmentResources);
            task.addProcess(newProcess);
            task.calculateCost();
            project.calculateCost();
            task.calculateDuration();
            project.calculateDuration();
            System.out.println(project.getCost());
            Task.updateTaskFileWithNewProcess(task.getId(), newProcess.getId());

            JOptionPane.showMessageDialog(this, "Process added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Return to the project panel
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            panel.add(new ProjectPanel(panel, project), "projectPanel");
            cardLayout.show(panel, "projectPanel");
        });
    }

    private <T extends Resource> List<T> getSelectedResources(DefaultListModel<T> model, Class<T> resourceClass) {
        List<T> selectedResources = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            T resource = model.getElementAt(i);
            if (resource != null && resourceClass.isInstance(resource)) {
                selectedResources.add(resource);
            }
        }
        return selectedResources;
    }
}

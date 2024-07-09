package ui.Comps;

import model.EquipmentResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentListPanel extends JPanel {
    private DefaultListModel<EquipmentResource> listModel;

    public EquipmentListPanel(String title) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFEF1"));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(title, SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        listModel = new DefaultListModel<>();
        JList<EquipmentResource> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new EquipmentResourceRenderer()); // Set custom renderer

        JScrollPane scrollPane = new JScrollPane(list);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.decode("#E0FF25"));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
    }

    public DefaultListModel<EquipmentResource> getListModel() {
        return listModel;
    }

    // Custom cell renderer for EquipmentResource objects
    private static class EquipmentResourceRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof EquipmentResource) {
                EquipmentResource resource = (EquipmentResource) value;
                setText(resource.getName()); // Display the name of the EquipmentResource
            }
            return this;
        }
    }
}

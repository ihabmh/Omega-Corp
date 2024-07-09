package ui.Comps;

import model.MaterialResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaterialListPanel extends JPanel {
    private DefaultListModel<MaterialResource> listModel;

    public MaterialListPanel(String title) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFEF1"));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(title, SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        listModel = new DefaultListModel<>();
        JList<MaterialResource> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new MaterialResourceRenderer()); // Set custom renderer

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

    public DefaultListModel<MaterialResource> getListModel() {
        return listModel;
    }

    // Custom cell renderer for MaterialResource objects
    private static class MaterialResourceRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof MaterialResource) {
                MaterialResource resource = (MaterialResource) value;
                setText(resource.getName()); // Display the name of the MaterialResource
            }
            return this;
        }
    }
}

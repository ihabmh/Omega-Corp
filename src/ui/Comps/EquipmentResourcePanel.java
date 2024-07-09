package ui.Comps;

import model.EquipmentResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentResourcePanel extends JPanel {
    private DefaultListModel<EquipmentResource> listModel;

    public EquipmentResourcePanel(String labelText, List<EquipmentResource> resources, DefaultListModel<EquipmentResource> listModel) {
        this.listModel = listModel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#FFFEF1"));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        setPreferredSize(new Dimension(400, 60));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setBackground(Color.decode("#FFFEF1"));

        JLabel resourceLabel = new JLabel(labelText, SwingConstants.LEFT);
        labelPanel.add(resourceLabel);
        labelPanel.add(Box.createHorizontalGlue());

        JPanel addResourcePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addResourcePanel.setBackground(Color.decode("#FFFEF1"));
        addResourcePanel.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));

        JComboBox<String> resourceOptionsDropdown = new JComboBox<>();
        for (EquipmentResource er : resources) {
            resourceOptionsDropdown.addItem(er.getName());
        }

        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.decode("#E0FF25"));
        addButton.setForeground(Color.BLACK);
        addButton.setBorderPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipmentResource er = resources.get(resourceOptionsDropdown.getSelectedIndex());
                if (er != null) {
                    listModel.addElement(er);
                }
            }
        });

        addResourcePanel.add(resourceOptionsDropdown);
        addResourcePanel.add(addButton);

        add(labelPanel);
        add(addResourcePanel);
    }
}

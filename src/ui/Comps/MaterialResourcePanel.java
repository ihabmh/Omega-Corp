package ui.Comps;

import model.MaterialResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MaterialResourcePanel extends JPanel {
    private DefaultListModel<MaterialResource> listModel;

    public MaterialResourcePanel(String labelText, List<MaterialResource> resources, DefaultListModel<MaterialResource> listModel) {
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
        for (MaterialResource mr : resources) {
            resourceOptionsDropdown.addItem(mr.getName());
        }

        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.decode("#E0FF25"));
        addButton.setForeground(Color.BLACK);
        addButton.setBorderPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialResource mr = resources.get(resourceOptionsDropdown.getSelectedIndex());
                if (mr != null) {
                    listModel.addElement(mr);
                }
            }
        });

        addResourcePanel.add(resourceOptionsDropdown);
        addResourcePanel.add(addButton);

        add(labelPanel);
        add(addResourcePanel);
    }
}

package ui.Comps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourcePanel extends JPanel {
    private DefaultListModel<String> listModel;

    public ResourcePanel(String labelText, String[] options, DefaultListModel<String> listModel) {
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

        JPanel addResourcePanel = new JPanel();
        addResourcePanel.setBackground(Color.decode("#FFFEF1"));
        addResourcePanel.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));

        JComboBox<String> resourceOptionsDropdown = new JComboBox<>(new DefaultComboBoxModel<>(options));
        resourceOptionsDropdown.setPreferredSize(new Dimension(334, 30));

        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.decode("#E0FF25"));
        addButton.setForeground(Color.BLACK);
        addButton.setBorderPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) resourceOptionsDropdown.getSelectedItem();
                if (!selectedItem.equals("Choose") && !listModel.contains(selectedItem)) {
                    listModel.addElement(selectedItem);
                }
            }
        });

        addResourcePanel.add(resourceOptionsDropdown);
        addResourcePanel.add(addButton);

        add(labelPanel);
        add(addResourcePanel);
    }
}

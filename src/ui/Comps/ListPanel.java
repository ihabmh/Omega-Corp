package ui.Comps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPanel extends JPanel {
    private DefaultListModel<String> listModel;

    public ListPanel(String title) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFEF1"));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));


        JLabel label = new JLabel(title, SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


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

    public DefaultListModel<String> getListModel() {
        return listModel;
    }
}

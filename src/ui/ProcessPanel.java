package ui;
import ui.Comps.*;


import java.awt.*;
import javax.swing.*;

public class ProcessPanel extends JPanel {
    JPanel header;
    JButton backBtn;
    JLabel processName;
    JPanel content;

    public ProcessPanel() {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        header = new JPanel(new BorderLayout());
        header.setBackground(Color.decode("#FFFEF1"));
        processName = new JLabel("Process Name");
        processName.setFont(new Font("Arial", Font.BOLD, 20));

        backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBorderPainted(false);

        header.add(processName, BorderLayout.WEST);
        header.add(backBtn, BorderLayout.EAST);

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.decode("#FFFEF1"));

        content.add(new HrPanel());
        content.add(new MrPanel());
        content.add(new ErPanel());

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
    }
}

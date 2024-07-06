package ui;
import ui.Comps.*;


import java.awt.*;
import javax.swing.*;
import model.Process;

public class ProcessPanel extends JPanel {
    JPanel header;
    JButton backBtn;
    JLabel processName;
    JPanel content;

    public ProcessPanel(JPanel panel, Process process) {
        setBackground(Color.decode("#FFFEF1"));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        header = new JPanel(new BorderLayout());
        header.setBackground(Color.decode("#FFFEF1"));
        processName = new JLabel(process.getName());
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

        content.add(new HrPanel(process));
        content.add(new MrPanel(process));
        content.add(new ErPanel(process));

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        backBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "projectPanel");
        });
    }
}

package ui.Comps;

import javax.swing.*;
import java.awt.*;

public class ProjectCardPanel extends JPanel {

    JLabel prjctName;
    JLabel prjctStatus;
    JLabel prjctDate;
    JButton viewBtn;



    public ProjectCardPanel(JPanel panel) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.decode("#E6E5D9"));


        prjctName = new JLabel("Project Name");
        prjctName.setFont(new Font("Arial", Font.BOLD, 16));
        prjctStatus = new JLabel("Project Status:");
        prjctDate = new JLabel("Date");
        viewBtn = new JButton("View");
        viewBtn.setBackground(Color.decode("#E0FF25"));
        viewBtn.setForeground(Color.BLACK);
        viewBtn.setBorderPainted(false);

        add(prjctName);
        add(Box.createHorizontalGlue());
        add(prjctStatus);
        add(Box.createHorizontalGlue());
        add(prjctDate);
        add(Box.createHorizontalGlue());
        add(viewBtn);


        viewBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "projectPanel");
        });

    }
}

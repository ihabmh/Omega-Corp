package ui.Comps;

import model.Project;
import model.Task;
import ui.ProjectPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProjectCardPanel extends JPanel {

    JLabel prjctName;
    JLabel prjctStatus;
    JLabel prjctCost;
    JButton viewBtn;



    public ProjectCardPanel(JPanel panel, Project project) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.decode("#E6E5D9"));

   


        prjctName = new JLabel(project.getName());
        prjctName.setFont(new Font("Arial", Font.BOLD, 16));
        prjctName.setPreferredSize(new Dimension(200, 30));

        prjctStatus = new JLabel("Project Status: "+ project.getStatus());



        prjctCost = new JLabel("Project Cost: $" + project.getCost());
        viewBtn = new JButton("View");
        viewBtn.setBackground(Color.decode("#E0FF25"));
        viewBtn.setForeground(Color.BLACK);
        viewBtn.setBorderPainted(false);

        add(prjctName);
        add(Box.createHorizontalGlue());
        add(prjctStatus);
        add(Box.createHorizontalGlue());
        add(prjctCost);
        add(Box.createHorizontalGlue());
        add(viewBtn);


        viewBtn.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            ProjectPanel pp = new ProjectPanel(panel, project);


            panel.add(pp, "projectPanel");
            cardLayout.show(panel, "projectPanel");
        });

    }
}

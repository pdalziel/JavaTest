import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JavaBallGUI extends JFrame implements ActionListener{

    //panels
    private JPanel displayPanel;
    private JPanel sidePanel;
    private JPanel rankingPanel;

    //buttons
    private JButton bExit;
    private JButton bLoad;
    private JButton bEnter;
    private JButton bRemove;
    private JButton bRank;

    private ArrayList<JLabel> matchesLabels;
    private ArrayList<JLabel> rankingLables;

    public JavaBallGUI(JavaBallApp app){
        setTitle("JavaBall Program");
        setSize(1080, 720);
        setLocation(50, 50);

        //add panels

        addSidePanel();
        addDisplayPanel();
        addRankingPanel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }

    private void addRankingPanel() {
        GridLayout gridLayout = new GridLayout(10,10);
        rankingPanel = new JPanel(gridLayout);
        add(rankingPanel,"South");
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Team Rankings");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        rankingPanel.setBorder(titledBorder);
        //todo add rank labels


    }

    private void addDisplayPanel() {
        GridLayout gridLayout = new GridLayout(28,1);
        displayPanel = new JPanel(gridLayout);
        add(displayPanel,"Center");
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Match Results");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        displayPanel.setBorder(titledBorder);
        // todo add match labels


    }

    private void addSidePanel() {
        GridLayout gridLayout = new GridLayout(6, 1);
        //add panel
        sidePanel = new JPanel(gridLayout);
        add(sidePanel, "West");
        sidePanel.add(new JLabel("Options",SwingConstants.CENTER));
        //add buttons
        bExit = new JButton("Save & Exit");
        bExit.addActionListener(this);
        bLoad = new JButton("Load Results");
        bLoad.addActionListener(this);
        bRemove = new JButton("Remove Team");
        bRemove.addActionListener(this);
        bEnter = new JButton("Enter Result");
        bEnter.setEnabled(false);
        bEnter.addActionListener(this);
        bRank = new JButton("Calculate Rankings");
        bRank.setEnabled(false);
        bRank.addActionListener(this);

        sidePanel.add(bExit);
        sidePanel.add(bLoad);
        sidePanel.add(bRemove);
        sidePanel.add(bEnter);
        sidePanel.add(bRank);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== bExit){
            //todo save function
            System.exit(0);
        }
        else if(e.getSource()==bRemove){
            // todo remove function
        }
        else if(e.getSource()==bLoad){
            // todo load function
        }
        else if(e.getSource()==bEnter){
            // todo enter function
        }
        else if(e.getSource()==bRank){
            // todo rank function
        }
    }
}

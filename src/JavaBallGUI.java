import entities.Match;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class JavaBallGUI extends JFrame implements ActionListener{

    //panels
    private JPanel displayPanel;
    private JPanel sidePanel;
    private JPanel rankingPanel;

    private int displayRows, rankingRows, sideRows;
    //buttons
    private JButton bExit;
    private JButton bLoad;
    private JButton bEnter;
    private JButton bRemove;
    private JButton bRank;

    private JOptionPane errMsg;
    private String errMsgTxt = "Tournament Cancelled: Not Enough Teams!";

    private ArrayList<JLabel> matchesLabels;
    private ArrayList<JLabel> rankingLables;

    private JavaBallApp app;

    public JavaBallGUI(JavaBallApp app){
        this.app = app;
        setTitle("JavaBall Program");
        setSize(1080, 720);
        setLocation(50, 50);
        sideRows = 6;
        displayRows = 28;
        rankingRows = 10;

        app.loadTeams();
        loadMatches();
        // Check teams loaded correctly
        if(!app.teamsLoaded){
            errMsg.showMessageDialog(null, "TeamsIn1.txt not found");
            System.exit(1);
        }
        // Check there are enough teams
        if(app.getNumberOfTeams() < 3){
            errMsg.showMessageDialog(null, errMsgTxt);
            System.exit(2);
        }
        //add panels
        addSidePanel();
        addDisplayPanel();
        addRankingPanel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }



    private void updateDisplay(){

        for (JLabel jLabel: loadMatches()){
            //System.out.println(jLabel.getText());
            displayPanel.add(jLabel);
        }
    }


    private ArrayList<JLabel> loadMatches() {
        //System.out.println();
        // todo could use string padding to ensure equal lengths?

        ArrayList<Match> matches;
        if(app.fixturesLoaded){
            matches =  app.getMatches();
        }
        else{
            matches =  app.getFixtures();
        }
        matchesLabels = new ArrayList<JLabel>();
        for(int i=0; i < displayRows; i++){
            matchesLabels.add(new JLabel(" ",SwingConstants.CENTER));
        }
        for(int i = 0; i < matches.size(); i++){
            if(matches.get(i).isResultExists()){
                matchesLabels.get(i).setText(matches.get(i).getTeam1Name() +  "    " + matches.get(i).getT1Score() + "    "
                        + matches.get(i).getTeam2Name()+  "    " + matches.get(i).getT2Score());
            }
            else{
                matchesLabels.get(i).setText(matches.get(i).getTeam1Name() + "     v     " + matches.get(i).getTeam2Name()
                        + "    *** no results yet ***    ");            }
        }
        return matchesLabels;
    }

    private void  clearDisplay () {
        for (JLabel jLabel : matchesLabels){
            jLabel.setText("");
            displayPanel.remove(jLabel);
        }
    }


    private void addRankingPanel() {
        GridLayout gridLayout = new GridLayout(rankingRows,10);
        rankingPanel = new JPanel(gridLayout);
        add(rankingPanel,"South");
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Team Rankings");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        rankingPanel.setBorder(titledBorder);
        //todo add rank labels


    }

    private void addDisplayPanel() {
        GridLayout gridLayout = new GridLayout(displayRows,1);
        displayPanel = new JPanel(gridLayout);
        add(displayPanel,"Center");
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Match Results");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        displayPanel.setBorder(titledBorder);
        matchesLabels = loadMatches();
        updateDisplay();


    }

    private void addSidePanel() {
        GridLayout gridLayout = new GridLayout(sideRows, 1);
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


        sidePanel.add(bLoad);
        sidePanel.add(bRemove);
        sidePanel.add(bEnter);
        sidePanel.add(bRank);
        sidePanel.add(bExit);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== bExit){
            //todo save/write out function
            System.exit(0);
        }
        else if(e.getSource()==bRemove){
            String teamName = JOptionPane.showInputDialog(null, "Select Team to Remove");
            if(!app.checkTeam(teamName)){
                 teamName = JOptionPane.showInputDialog(null, "Team not Found - Select Team to Remove");
            }
            if (app.removeTeam(teamName)){
                JOptionPane.showMessageDialog(null, errMsgTxt);
                System.exit(2);
            }
            // update the display
            clearDisplay();
            matchesLabels = loadMatches();
            updateDisplay();

        }
        else if(e.getSource()==bLoad){
            try {
                app.loadResults();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
            // update the display
            clearDisplay();
            matchesLabels = loadMatches();
            updateDisplay();
            // Enable manual score entry
            bEnter.setEnabled(true);

        }
        else if(e.getSource()==bEnter){
            // todo enter function
            // display match and request score
            // display inputdialog
            // parse and check score inputs
            // if more scores to enter, repeat

            // update the display
            clearDisplay();
            matchesLabels = loadMatches();
            updateDisplay();
            // Enable generation of tournament rankings
            bRank.setEnabled(true);
        }
        else if(e.getSource()==bRank){
            // todo rank function
        }
    }
}

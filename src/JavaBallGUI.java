import entities.Match;
import entities.Team;

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
    private boolean ranked = false;

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
        ArrayList<Match> matches;
        if(app.fixturesLoaded){
            matches =  app.getMatches();
        }
        else{
            matches =  app.getFixtures();
        }
        matchesLabels = new ArrayList<JLabel>();
        for(int i=0; i < displayRows; i++){
            matchesLabels.add(new JLabel("",SwingConstants.CENTER));
        }
        for(int i = 0; i < matches.size(); i++){
            if(matches.get(i).hasResults()){
                matchesLabels.get(i).setText(matches.get(i).getTeam1Name() +  " " + matches.get(i).getT1Score() + " "
                        + matches.get(i).getTeam2Name()+  " " + matches.get(i).getT2Score());
            }
            else{
                matchesLabels.get(i).setText(matches.get(i).getTeam1Name() + " v " + matches.get(i).getTeam2Name()
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
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Tournament Rankings");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        rankingPanel.setBorder(titledBorder);
        rankingLables = new ArrayList<JLabel>();

        // Create 10 * 10 grid to display rankings
        for (int i = 0; i < 100; i++){
            rankingLables.add(new JLabel(""));
            rankingPanel.add(rankingLables.get(i));
        }
        // Add top row headers
        rankingLables.get(3).setText("Matches");
        rankingLables.get(5).setText("Goals");
        rankingLables.get(7).setText("Match");
        rankingLables.get(8).setText("Goal");
        // Add second row headers
        rankingLables.get(10).setText("Team");
        rankingLables.get(11).setText("Rank");
        rankingLables.get(12).setText("Won");
        rankingLables.get(13).setText("Drawn");
        rankingLables.get(14).setText("Lost");
        rankingLables.get(15).setText("For");
        rankingLables.get(16).setText("Against");
        rankingLables.get(17).setText("Points");
        rankingLables.get(18).setText("Diff");
        rankingLables.get(19).setText("Medal");
        // Display team rankings
        displayTeamRankings();
    }

    private void displayTeamRankings() {
        int offset = 20;
        for (Team team : app.getTeams()){
            rankingLables.get(offset++).setText(team.getPaddedName());
            rankingLables.get(offset++).setText("" + team.getRank());
            rankingLables.get(offset++).setText("" + team.getWon());
            rankingLables.get(offset++).setText("" + team.getDraws());
            rankingLables.get(offset++).setText("" + team.getLost());
            rankingLables.get(offset++).setText("" + team.getGoalsFor());
            rankingLables.get(offset++).setText("" + team.getGoalsAgainst());
            rankingLables.get(offset++).setText("" + team.getPoints());
            rankingLables.get(offset++).setText("" + team.getGoalDifference());
            rankingLables.get(offset++).setText("" + team.getMedal());
        }
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
            app.saveToFile(ranked);
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
            ArrayList<Match> matches = app.getMatches();
            for(Match match : matches){
                getScoreInput(match);
            }
            clearDisplay();
            matchesLabels = loadMatches();
            updateDisplay();
            // Enable generation of tournament rankings
            bRank.setEnabled(true);
        }
        else if(e.getSource()==bRank){
            app.rankTeams();
            ranked = true;
            displayTeamRankings();
        }
    }

    private void getScoreInput(Match match) {
        String scores;
        boolean scoreEntered = false;
        if(!match.hasResults()){
            while(!scoreEntered){
                JOptionPane.showMessageDialog(null,"Enter scores for " + match.getTeam1Name() + "    v    " + match.getTeam2Name());
                scores =  JOptionPane.showInputDialog(null, "Scores must in the range 0-9 and separated by a space");
                scores = scores.trim();
                String[] parts = scores.split(" ");
                if(parts.length > 2){
                    continue;
                }
                int t1Score = 0;
                int t2Score = 0;
                try {
                    t1Score = new Integer(parts[0]);
                    t2Score = new Integer(parts[1]);
                } catch (Exception e1) {
                    e1.getMessage();
                    scores =  JOptionPane.showInputDialog(null, "Scores must in the range 0-9 and separated by a space");
                    continue;
                }
                if( t1Score <  0 || t2Score <0 || t1Score > 9 || t2Score > 9){
                    JOptionPane.showInputDialog(null, "Scores must in the range 0-9 and separated by a space");
                }
                else {
                    match.setT1Score(t1Score);
                    match.setT2Score(t2Score);
                    scoreEntered = true;
                }
            }
            match.setResultExists(true);
        }
    }
}

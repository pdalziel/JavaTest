import entities.Match;
import entities.Team;
import utils.TeamByPointsComparator;
import utils.ResultsFileReader;
import utils.ResultsFileWriter;
import utils.TeamFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class JavaBallApp {

    private TeamFileReader teamReader;
    private ResultsFileReader resultsReader;
    private ResultsFileWriter writer;

    private String teamsInFile;
    private String resultsInFile;

    private ArrayList<Team> teams;
    private ArrayList<Match> matches;
    boolean teamsLoaded = false;
    boolean fixturesLoaded = false;

    public JavaBallApp(String teamsInFile, String resultsInFile) {
        this.teamsInFile = teamsInFile;
        this.resultsInFile = resultsInFile;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Team> loadTeams(){
        teamReader = new TeamFileReader();
        try {
            teams = teamReader.readTeamFile(teamsInFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        teamsLoaded = true;
        return teams;
    }

    public int getNumberOfTeams() {
        return teams.size();
    }

    private ArrayList<Match> getFixtures(ArrayList<Team> teams){
        matches = new ArrayList<Match>();
        for (int i = 0; i < teams.size() ; i++) {
            for (int j = i+1; j < teams.size(); j++) {
                matches.add(new Match(teams.get(i), teams.get(j)));
            }
        }
        fixturesLoaded = true;
        return matches;
    }
    public ArrayList<Match>getFixtures(){
        return getFixtures(teams);
    }


    public boolean removeTeam(String teamName) {
        removeFromTeamMatches(teamName);
        removeFromTeams(teamName);
        return teams.size()<3;
    }

    private void removeFromTeams(String teamName) {
        int counter = 0;
        while(counter < teams.size()){
            if(teams.get(counter).getName().equals(teamName))
                teams.remove(counter);
            else
                counter++;
        }
    }

    private void removeFromTeamMatches(String teamName) {
        int counter = 0;
        while(counter < matches.size()){
            if(matches.get(counter).getTeam1Name().equals(teamName) || matches.get(counter).getTeam2Name().equals(teamName))
                matches.remove(counter);
            else
                counter++;
        }
    }

    public boolean checkTeam(String teamName) {
        for (Team t : teams){
            if(t.getName().equals(teamName));
            return true;
        }
        return false;

    }

    public void loadResults() throws IOException {
        ResultsFileReader reader = new ResultsFileReader();
        reader.readResultsInFile(resultsInFile, matches);
        this.teamsLoaded = true;

    }

    public boolean getTeamsLoaded() {
        return this.teamsLoaded;
    }

    public void saveToFile(boolean ranked) {
        writer = new ResultsFileWriter();
        if (ranked){
            writer.writeRankings(teams);
        }
        else {
            writer.writeMatchResults(matches);
        }

    }

    public void rankTeams() {
        // TODO handle equally ranked teams
        for (Match match : matches){
            match.calculateRank();
        }
        for (Team team : teams){
            team.setGoalDifference();
            team.setPoints();
            //System.out.println(team.toDebugString());
        }
        Collections.sort(teams, new TeamByPointsComparator());

        // TODO if 2+ teams have same points & goal diff award same rank
        for (int i = 0; i < teams.size(); i++){
            teams.get(i).setRank(i+1);
            System.out.println(teams.get(i).toDebugString());
        }
       for (Team team : teams){
           if(team.getRank() == 1){
               team.setMedal("Gold");
           }
           else if(team.getRank() == 2){
               team.setMedal("Silver");
           }
           else if(team.getRank() == 3){
               team.setMedal("Bronze");
           }
      }

    }
}

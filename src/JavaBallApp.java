import entities.Match;
import entities.Team;
import utils.ResultsFileReader;
import utils.ResultsFileWriter;
import utils.TeamFileReader;

import java.io.IOException;
import java.util.ArrayList;

public class JavaBallApp {

    private TeamFileReader teamReader;
    private ResultsFileReader resultsReader;
    private ResultsFileWriter resultsWriter;

    private String teamsInFile;
    private String resultsInFile;

    private ArrayList<Team> teams;
    private ArrayList<Match> matches;
    boolean teamsLoaded = false;

    public JavaBallApp(String teamsInFile, String resultsInFile) {
        this.teamsInFile = teamsInFile;
        this.resultsInFile = resultsInFile;

    }

    public ArrayList<Team> loadTeams(){
        teamReader = new TeamFileReader();
        try {
            teams = teamReader.readTeamFile(teamsInFile);
        } catch (IOException e) {

            e.printStackTrace();
        }
        teamsLoaded = true;
        return teams;

    }
}

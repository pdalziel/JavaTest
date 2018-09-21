package utils;

import entities.Match;
import entities.Team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultsFileWriter {

    public void writeMatchResults(ArrayList<Match> matches) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("ResultsOut.txt"));
            for (Match result  : matches) {
                writer.write(result.toString());
                writer.newLine();
            }
            writer.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void writeRankings(ArrayList<Team> teams) {
        String headerString1 = "\t\t\t\t\t\tMatches\t\t\tGoals\t\t\t\tMatch\t\t\tGoal";
        String headerString2 = "Team\t\tRank\t\tWon\t\tLost\tFor\t\tAgainst\t\tPoints\t\tDiff\t\t\t\tMedal";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("ResultsOut.txt"));
            writer.write(headerString1);
            writer.newLine();
            writer.write(headerString2);
            writer.newLine();
            for (Team team  : teams) {
                writer.write(team.toString());
                writer.newLine();
            }
            writer.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

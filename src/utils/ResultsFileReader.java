package utils;

import entities.Match;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultsFileReader {

    public void readResultsInFile(String fileName, ArrayList<Match> matches) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(" ");
            for(int i = 0; i < matches.size(); i++){
                if(matches.get(i).checkTeams(parts[0],parts[2])){
                    if(parts[0].equals(matches.get(i).getTeam1Name())){
                        matches.get(i).setT1Score(Integer.parseInt(parts[1]));
                        matches.get(i).setT2Score(Integer.parseInt(parts[3]));
                    }
                    else{
                        matches.get(i).setT2Score(Integer.parseInt(parts[1]));
                        matches.get(i).setT1Score(Integer.parseInt(parts[3]));
                    }
                    matches.get(i).setResultExists(true);
                }
            }
        }

    }

}


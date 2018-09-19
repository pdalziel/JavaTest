package utils;

import entities.Team;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TeamFileReader  {


    public ArrayList<Team> readTeamFile(String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        ArrayList<Team> teams = new ArrayList<Team>();
        while (scanner.hasNextLine()){
            teams.add(new Team(scanner.nextLine(),0));
        }
        Collections.sort(teams);
        return teams;
    }
}

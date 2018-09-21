package utils;

import entities.Team;

import java.util.Comparator;

public class TeamByPointsComparator implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        int points = (o2.getPoints()) - o1.getPoints();
        if(points != 0){
            return points;
        }
        return (o2.getGoalDifference()) - o1.getGoalDifference();
    }
}
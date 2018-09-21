package entities;

import utils.Padder;

import java.util.Comparator;

public class Team implements Comparable<Team>{
    String name;
    String medal;
    int points;
    int goalDifference;
    int rank;
    int won;
    int lost;
    int draws;
    int goalsFor;
    int goalsAgainst;


    public Team(String name, int points) {
        this.name = name;
        this.points = points;
        goalDifference = 0;
        rank = 0;
        won = 0;
        lost = 0;
        draws = 0;
        goalsFor = 0;
        goalsAgainst = 0;
        medal = "";

    }


    public String toDebugString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", medal='" + medal + '\'' +
                ", points=" + points +
                ", goalDifference=" + goalDifference +
                ", rank=" + rank +
                ", won=" + won +
                ", lost=" + lost +
                ", draws=" + draws +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                '}';
    }

    @Override
    public String toString() {
        Padder padder = new Padder();
        String pName = padder.padString(name);
        return ( pName  + "\t\t" +
                rank  + "\t\t" +
                won + "\t\t" +
                lost + "\t\t" +
                goalsFor + "\t\t" +
                goalsAgainst  + "\t\t\t" +
                points + "\t\t\t" +
                goalDifference + "\t\t\t\t\t" +
                medal);
    }

    public String getName() {
        return name;
    }
    public String getPaddedName(){
        Padder padder = new Padder();
        String pName = padder.padString(this.name);
        return pName;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        this.points = this.won*3 + this.draws;
        System.out.println( "points = " + this.won*3 + this.draws);
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference() {
        this.goalDifference = this.goalsFor - this.goalsAgainst;
        System.out.println( "goal diff = " + goalDifference + "  " + (this.goalsFor - this.goalsAgainst));
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getWon() {
        return won;
    }

    public void setWon() {
        this.won++;
    }

    public int getLost() {
        return lost;
    }

    public void setLost() {
        this.lost++;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws() {
        this.draws++;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor += goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst += goalsAgainst;
    }

    @Override
    public int compareTo(Team o) {
        return name.compareToIgnoreCase(o.name);
    }


}

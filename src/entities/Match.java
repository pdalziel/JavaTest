package entities;

public class Match {
    private Team team1;
    private Team team2;
    private int t1Score;
    private int t2Score;
    private boolean resultExists;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.resultExists = false;
    }

    public String getTeam1Name() {
        return team1.name;
    }

    public String getTeam2Name() {
        return team2.name;
    }


    public int getT1Score() {
        return t1Score;
    }

    public void setT1Score(int t1Score) {
        this.t1Score = t1Score;
    }

    public int getT2Score() {
        return t2Score;
    }

    public void setT2Score(int t2Score) {
        this.t2Score = t2Score;
    }

    public boolean isResultExists() {
        return resultExists;
    }

    public void setResultExists(boolean resultExists) {
        this.resultExists = resultExists;
    }

    public boolean checkTeams(String t1Name, String t2Name){
        return ((t1Name.equals(team1.getName()) && t2Name.equals(team2.getName())) || (t1Name.equals(team2.getName()) && (t2Name.equals(team1.getName()))));
    }
    public void calculateRanks(){
        team1.setGoalsFor(t1Score);
        team2.setGoalsFor(t2Score);
        team1.setGoalsAgainst(t2Score);
        team2.setGoalsAgainst(t1Score);

        if(t1Score == t2Score){
            team1.setDraws();
            team2.setDraws();
        }
        else if(t1Score > t2Score){
            team1.setWon();
            team2.setLost();

        }
        else {
            team1.setLost();
            team2.setWon();
        }
    }
}



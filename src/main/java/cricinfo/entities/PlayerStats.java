package cricinfo.entities;

public class PlayerStats {
    private int runs;
    private int ballsPlayed;
    private int wickets;
    public PlayerStats() {
        ballsPlayed = 0;
        wickets = 0;
    }
    public void updateRuns(int runsScored) {
        runs += runsScored;
    }
    public void incrementBallsPlayed() {
        ballsPlayed +=1;
    }
    public void incrementWickets() {
        wickets +=1;
    }
    @Override
    public String toString() {
        return "Runs: " + runs + ", Balls Played: " + ballsPlayed + ", Wickets: " + wickets;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }
}

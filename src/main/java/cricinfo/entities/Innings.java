package cricinfo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cricinfo.enums.ExtraType;
import cricinfo.enums.PlayerRole;

public class Innings {
    private final Team battingTeam;
    private final Team bowlingTeam;
    private int score;
    private int wickets;
    private final List<Ball> balls;
    private final Map<Player, PlayerStats> playerStats;

    public Innings(Team battingTeam, Team bowlingTeam) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.score = 0;
        this.wickets = 0;
        this.balls = new ArrayList<>();
        this.playerStats = new ConcurrentHashMap<>();
        for(Player player: battingTeam.getPlayers()) {
            playerStats.put(player, new PlayerStats());
        }
        for(Player player: bowlingTeam.getPlayers()) {
            playerStats.put(player, new PlayerStats());
        }
    }
    public void addBall(Ball ball) {
        this.balls.add(ball);
        int runsScored = ball.getRunsScored();
        this.score += runsScored;
        if (ball.getExtraType() == ExtraType.WIDE || ball.getExtraType() == ExtraType.NO_BALL) {
            this.score += 1;
        } else {
            ball.getFacedBy().getStats().updateRuns(runsScored);
            ball.getFacedBy().getStats().incrementBallsPlayed();
            playerStats.get(ball.getFacedBy()).updateRuns(runsScored);
            playerStats.get(ball.getFacedBy()).incrementBallsPlayed();
        }
        if (ball.isWicket()) {
            this.wickets += 1;
            ball.getBowledBy().getStats().incrementWickets();
            playerStats.get(ball.getBowledBy()).incrementWickets();
        }
    }
    public void printPlayerStats() {
        for(Map.Entry<Player, PlayerStats> entry: playerStats.entrySet()) {
            Player player = entry.getKey();
            PlayerStats stats = entry.getValue();
            if (stats.getBallsPlayed() > 0 || stats.getWickets() > 0) {
                System.out.println("Player: " + player.getName() + " - Stats: " + stats);
            }
        }
    }
    public double getOvers() {
        int validBalls = (int) balls.stream()
                .filter(ball -> ball.getExtraType() != ExtraType.WIDE && ball.getExtraType() != ExtraType.NO_BALL)
                .count();
        int completeOvers = validBalls/6;
        int ballsInCurrentOver = validBalls%6;
        return completeOvers + (ballsInCurrentOver/10.0);
    }

    public int getWickets() {
        return wickets;
    }

    public int getScore() {
        return score;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Map<Player, PlayerStats> getPlayerStats() {
        return playerStats;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }
}

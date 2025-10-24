package cricinfo.entities;

import cricinfo.enums.MatchStatus;
import cricinfo.observer.MatchObserver;
import cricinfo.state.MatchState;
import cricinfo.state.ScheduledState;
import cricinfo.strategy.MatchFormatStrategy;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private final String id;
    private final Team team1;
    private final Team team2;
    private final MatchFormatStrategy formatStrategy;
    private final List<Innings> innings;
    private MatchState currentState;
    private MatchStatus currentStatus;
    private final List<MatchObserver> observers = new ArrayList<>();
    private Team winner;
    private String resultMessage;

    public Match(String id, Team team1, Team team2, MatchFormatStrategy formatStrategy) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.formatStrategy = formatStrategy;
        this.innings = new ArrayList<>();
        this.innings.add(new Innings(team1, team2));
        this.currentState = new ScheduledState();
        this.resultMessage = "";
    }
    // State Pattern Methods
    public void processBall(Ball ball) {
        currentState.processBall(this, ball);
    }
    public void startNextInnings() {
        currentState.startNextInnings(this);
    }
    public void createNewInnings() {
        if(innings.size() >= formatStrategy.getTotalInnings()) {
            System.out.println("Cannot create a new innings, match has already reached its limit.");
            return;
        }
        // Swap the teams for the next innings
        Innings nextInnings = new Innings(this.team2, this.team1);
        this.innings.add(nextInnings);
    }
    // Observer Pattern Methods
    public void addObserver(MatchObserver observer) { observers.add(observer); }
    public void removeObserver(MatchObserver observer) { observers.remove(observer); }
    public void notifyObservers(Ball ball) {
        for (MatchObserver observer : observers) {
            observer.update(this, ball);
        }
    }
    public Innings getCurrentInnings() { return innings.get(innings.size() - 1); }

    public void setCurrentState(MatchState currentState) {
        this.currentState = currentState;
    }
    public void setState(MatchState state) {
        this.currentState = state;
    }
    public MatchStatus getCurrentStatus() {
        return currentStatus;
    }

    public List<Innings> getInnings() {
        return innings;
    }

    public MatchState getCurrentState() {
        return currentState;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public Team getWinner() {
        return winner;
    }

    public String getId() {
        return id;
    }

    public void setCurrentStatus(MatchStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public MatchFormatStrategy getFormatStrategy() {
        return formatStrategy;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
package cricinfo.state;

import cricinfo.entities.Ball;
import cricinfo.entities.Match;
import cricinfo.enums.MatchStatus;

public class InBreakState implements MatchState{
    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println("Cannot process a ball as match is in a break");
    }

    @Override
    public void startNextInnings(Match match) {
        System.out.println("Starting the next innings...");
        match.createNewInnings();
        match.setCurrentState(new LiveState());
        match.setCurrentStatus(MatchStatus.LIVE);
    }
}

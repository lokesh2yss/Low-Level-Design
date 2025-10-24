package cricinfo.state;

import cricinfo.entities.Ball;
import cricinfo.entities.Match;

public class FinishedState implements MatchState{
    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println("ERROR: Cannot process a ball for a finished match.");
    }
}

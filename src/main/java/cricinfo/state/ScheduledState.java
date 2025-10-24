package cricinfo.state;

import cricinfo.entities.Ball;
import cricinfo.entities.Match;

public class ScheduledState implements MatchState{
    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println("Cannot process a ball from a match that is scheduled");
    }
}

package cricinfo.observer;

import cricinfo.entities.Ball;
import cricinfo.entities.Match;
import cricinfo.enums.MatchStatus;

public class UserNotifier implements MatchObserver{
    @Override
    public void update(Match match, Ball lastBall) {
        if (match.getCurrentStatus() == MatchStatus.FINISHED) {
            System.out.println("[NOTIFICATION]: Match has finished!");
        } else if (match.getCurrentStatus() == MatchStatus.IN_BREAK) {
            System.out.println("[NOTIFICATION]: Inning has ended!");
        } else if (lastBall.isWicket()) {
            System.out.println("[NOTIFICATION]: Wicket! A player is out.");
        } else if (lastBall.isBoundary()) {
            System.out.printf("[NOTIFICATION]: It's a boundary! %d runs.%n", lastBall.getRunsScored());
        }
    }
}

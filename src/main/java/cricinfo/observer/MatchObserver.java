package cricinfo.observer;

import cricinfo.entities.Ball;
import cricinfo.entities.Match;

public interface MatchObserver {
    void update(Match match, Ball lastBall);
}

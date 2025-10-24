package cricinfo.repositories;

import cricinfo.entities.Match;
import cricinfo.observer.MatchObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MatchRepository {
    private final Map<String, Match> matches = new HashMap<>();
    public void save(Match match) {
        matches.put(match.getId(), match);
    }
    public Optional<Match> findById(String matchId) {
        Match match = matches.get(matchId);
        return Optional.ofNullable(match);
    }
}

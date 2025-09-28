package auto_complete.strategy.interfaces;

import auto_complete.entities.Suggestion;

import java.util.List;

public interface RankingStrategy {
    List<Suggestion> rank(List<Suggestion> suggestions);
}

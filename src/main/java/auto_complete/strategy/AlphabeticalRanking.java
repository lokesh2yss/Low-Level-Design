package auto_complete.strategy;

import auto_complete.entities.Suggestion;
import auto_complete.strategy.interfaces.RankingStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalRanking implements RankingStrategy {
    @Override
    public List<Suggestion> rank(List<Suggestion> suggestions) {
        return suggestions.stream()
                .sorted(Comparator.comparing(Suggestion::getWord))
                .collect(Collectors.toList());


    }
}

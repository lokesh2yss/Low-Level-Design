package search_engine.strategy;

import search_engine.entities.SearchResult;

import java.util.Comparator;
import java.util.List;

public class ScoreThenAlphabeticalRankingStrategy implements RankingStrategy{
    @Override
    public void rank(List<SearchResult> results) {
        // Create a composite comparator.
        // 1. Primary sort: by score, descending.
        // 2. Secondary sort (for ties): by document title, ascending.
        Comparator<SearchResult> compositeComparator = Comparator.comparing(SearchResult::getScore).reversed()
                .thenComparing(result -> result.getDocument().getTitle());
        results.sort(compositeComparator);
    }
}

package search_engine.strategy;

import search_engine.entities.SearchResult;

import java.util.Comparator;
import java.util.List;

public class ScoreBasedRankingStrategy implements RankingStrategy{
    @Override
    public void rank(List<SearchResult> results) {
        // Sorts purely by score in descending order.
        results.sort(Comparator.comparing(SearchResult::getScore).reversed());
    }
}

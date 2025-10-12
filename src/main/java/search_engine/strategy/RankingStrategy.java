package search_engine.strategy;

import search_engine.entities.SearchResult;

import java.util.List;

public interface RankingStrategy {
    void rank(List<SearchResult> results);
}

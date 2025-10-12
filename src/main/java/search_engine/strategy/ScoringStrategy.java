package search_engine.strategy;

import search_engine.entities.Document;
import search_engine.entities.Posting;

public interface ScoringStrategy {
    double calculateScore(String term, Posting posting, Document document);
}

package search_engine.strategy;

import search_engine.entities.Document;
import search_engine.entities.Posting;

public class TermFrequencyScoringStrategy implements ScoringStrategy{
    @Override
    public double calculateScore(String term, Posting posting, Document document) {
        // The simplest strategy: score is just the term frequency.
        return posting.getFrequency();
    }
}

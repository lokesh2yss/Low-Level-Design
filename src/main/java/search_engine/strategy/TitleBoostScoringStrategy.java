package search_engine.strategy;

import search_engine.entities.Document;
import search_engine.entities.Posting;

public class TitleBoostScoringStrategy implements ScoringStrategy{
    private static final double TITLE_BOOST_FACTOR = 1.5;
    @Override
    public double calculateScore(String term, Posting posting, Document document) {
        double score = posting.getFrequency();
        // Give a boost if the term appears in the title.
        if(document.getTitle().toLowerCase().contains(term)) {
            score *= TITLE_BOOST_FACTOR;
        }
        return score;
    }
}

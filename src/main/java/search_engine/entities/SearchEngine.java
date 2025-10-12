package search_engine.entities;

import search_engine.strategy.RankingStrategy;
import search_engine.strategy.ScoreBasedRankingStrategy;
import search_engine.strategy.ScoringStrategy;
import search_engine.strategy.TitleBoostScoringStrategy;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngine {
    private static SearchEngine instance;
    private final InvertedIndex index;
    private final DocumentStore store;
    private RankingStrategy rankingStrategy;
    private ScoringStrategy scoringStrategy;
    private SearchEngine() {
        index = new InvertedIndex();
        store = new DocumentStore();
        rankingStrategy = new ScoreBasedRankingStrategy();
        scoringStrategy = new TitleBoostScoringStrategy();
    }
    public static synchronized SearchEngine getInstance() {
        if(instance == null) {
            instance = new SearchEngine();
        }
        return instance;
    }
    public void setRankingStrategy(RankingStrategy strategy) {
        this.rankingStrategy = strategy;
    }
    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }
    public void indexDocument(Document document) {
        store.addDocument(document);
        Map<String, Integer> termFrequencies = new HashMap<>();
        // Tokenize title and content. Split by non-word characters.
        String text = (document.getTitle()+ " " + document.getContent()).toLowerCase();
        String[] tokens = text.split("\\W+");
        // Calculate term frequencies for the current document.
        for(String token: tokens) {
            if(!token.isEmpty()) {
                termFrequencies.merge(token, 1, Integer::sum);
            }
        }
        for(Map.Entry<String, Integer> entry: termFrequencies.entrySet()) {
            index.add(entry.getKey(), document.getId(), entry.getValue());
        }
    }
    public void indexDocuments(List<Document> documents) {
        for(Document doc: documents) {
            indexDocument(doc);
        }
    }
    public List<SearchResult> search(String query) {
        String processedQuery = query.toLowerCase();
        List<Posting> postings = index.getPostings(processedQuery);
        // 2. Map postings to SearchResult objects.
        List<SearchResult> results = new ArrayList<>();
        for(Posting posting: postings) {
            Document doc = store.getDocument(posting.getDocumentId());
            if(doc != null) {
                double score = scoringStrategy.calculateScore(processedQuery, posting, doc);
                results.add(new SearchResult(doc, score));

            }
        }
        rankingStrategy.rank(results);
        return results;
    }
}

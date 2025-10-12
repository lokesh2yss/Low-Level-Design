package search_engine;

import search_engine.entities.Document;
import search_engine.entities.SearchEngine;
import search_engine.entities.SearchResult;
import search_engine.strategy.ScoreBasedRankingStrategy;
import search_engine.strategy.ScoreThenAlphabeticalRankingStrategy;
import search_engine.strategy.TermFrequencyScoringStrategy;
import search_engine.strategy.TitleBoostScoringStrategy;

import java.util.Arrays;
import java.util.List;

public class SearchEngineDemo {
    public static void main(String[] args) {
        // 1. Create a Search Engine instance
        SearchEngine engine = SearchEngine.getInstance();

        // 2. Create a predefined set of documents
        List<Document> documents = Arrays.asList(
                new Document("doc1", "Java Performance", "Java is a high-performance language. Tuning Java applications is key."),
                new Document("doc2", "Introduction to Python", "Python is a versatile language, great for beginners."),
                new Document("doc3", "Advanced Java Concepts", "This document covers advanced topics in Java programming."),
                new Document("doc4", "Python vs. Java", "A document comparing Python and Java for web development. Java is faster.")
        );

        // 3. Index the documents
        System.out.println("Indexing documents...");
        engine.indexDocuments(documents);
        System.out.println("Indexing complete.\n");

        System.out.println("====== TermFrequency Scoring + ScoreBased Ranking ======");
        engine.setScoringStrategy(new TermFrequencyScoringStrategy());
        engine.setRankingStrategy(new ScoreBasedRankingStrategy());

        performSearch(engine, "java");
        performSearch(engine, "language");
        performSearch(engine, "performance");

        System.out.println("\n====== TitleBoost Scoring + Score-then-Alphabetical Ranking ======");
        engine.setScoringStrategy(new TitleBoostScoringStrategy());
        engine.setRankingStrategy(new ScoreThenAlphabeticalRankingStrategy());

        performSearch(engine, "java");
        performSearch(engine, "language");
        performSearch(engine, "performance");

        // No results
        performSearch(engine, "paint");
    }

    private static void performSearch(SearchEngine engine, String query) {
        System.out.println("--- Searching for: '" + query + "' ---");
        List<SearchResult> results = engine.search(query);

        if (results.isEmpty()) {
            System.out.println("  No results found.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println("Rank " + (i + 1) + ":" + results.get(i));
            }
        }
        System.out.println();

    }
}

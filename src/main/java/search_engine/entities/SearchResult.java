package search_engine.entities;

public class SearchResult {
    private final Document document;
    private final double score;

    public SearchResult(Document document, double score) {
        this.document = document;
        this.score = score;
    }

    public Document getDocument() {
        return document;
    }

    public double getScore() {
        return score;
    }
    @Override
    public String toString() {
        return "  - " + document.getTitle() + " (Score: " + String.format("%.2f", score) + ")";
    }
}

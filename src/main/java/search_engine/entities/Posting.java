package search_engine.entities;

public class Posting {
    private final String documentId;
    private final int frequency;

    public Posting(String documentId, int frequency) {
        this.documentId = documentId;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getDocumentId() {
        return documentId;
    }
}

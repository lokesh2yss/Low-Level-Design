package search_engine.entities;


import java.util.HashMap;
import java.util.Map;

public class DocumentStore {
    private final Map<String, Document> store = new HashMap<>();
    public void addDocument(Document document) {
        store.put(document.getId(), document);
    }
    public Document getDocument(String documentId) {
        return store.get(documentId);
    }
}

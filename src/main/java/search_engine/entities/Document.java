package search_engine.entities;

public class Document {
    private final String id;
    private final String title;
    private final String content;

    public Document(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Document(id=" + id + ", title='" + title + "')";
    }
}

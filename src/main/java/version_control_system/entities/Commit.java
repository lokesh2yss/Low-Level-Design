package version_control_system.entities;

import version_control_system.composite.Directory;

import java.time.LocalDateTime;
import java.util.UUID;

public class Commit {
    private final String id;
    private final String message;
    private final String author;
    private final LocalDateTime timestamp;
    private final Commit parent;
    private final Directory rootSnapshot;

    public Commit(String message, String author, Commit parent, Directory rootSnapshot) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.author = author;
        this.parent = parent;
        this.rootSnapshot = rootSnapshot;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public Commit getParent() {
        return parent;
    }

    public Directory getRootSnapshot() {
        return rootSnapshot;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

package chat_application.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public final class Message {
    private final String id;
    private final User sender;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(User sender, String content) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s: %s", timestamp, sender.getName(), content);
    }
}

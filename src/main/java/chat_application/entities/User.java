package chat_application.entities;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    public void onMessageReceived(Message message, Chat chatContext) {
        System.out.printf("[Notification for %s in chat '%s'] %s: %s\n",
                this.getName(), chatContext.getName(this), message.getSender().getName(), message.getContent());
    }
    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        User u = (User) other;
        return id.equals(u.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}

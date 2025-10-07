package chat_application.entities;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Chat {
    protected final String id;
    protected final List<User> members;
    protected final List<Message> messages;

    protected Chat() {
        this.id = UUID.randomUUID().toString();
        members = new CopyOnWriteArrayList<>();
        messages = new CopyOnWriteArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Message> getMessages() {
        return List.copyOf(messages);
    }

    public List<User> getMembers() {
        return List.copyOf(members);
    }
    public void addMessage(Message message) {
        this.messages.add(message);
    }
    public abstract String getName(User perspectiveUser);
}

package stack_overflow.entities;

import stack_overflow.enums.EventType;

public class Event {
    private final EventType type;
    private final User actor;
    private final Post targetPost;


    public Event(EventType type, User actor, Post targetPost) {
        this.type = type;
        this.actor = actor;
        this.targetPost = targetPost;
    }

    public EventType getType() {
        return type;
    }

    public User getActor() {
        return actor;
    }

    public Post getTargetPost() {
        return targetPost;
    }
}

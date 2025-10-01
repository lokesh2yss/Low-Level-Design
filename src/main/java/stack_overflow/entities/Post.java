package stack_overflow.entities;

import stack_overflow.entities.interfaces.PostObserver;
import stack_overflow.enums.EventType;
import stack_overflow.enums.VoteType;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Post extends Content{
    private final AtomicInteger votesCount = new AtomicInteger(0);
    private final Map<String, VoteType> voters = new ConcurrentHashMap<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<PostObserver> observers = new ArrayList<>();
    public Post(String id, String body, User author) {
        super(id, body, author);
    }
    public void addObserver(PostObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(PostObserver observer) {
        this.observers.remove(observer);
    }
    public void notifyObservers(Event event) {
        for(PostObserver observer: observers) {
            observer.onPostEvent(event);
        }
    }
    public synchronized void vote(User user, VoteType voteType) {
        String userId = user.getId();
        if(voters.get(userId) == voteType) {
            return; //already voted
        }
        int scoreChange = 0;
        if(voters.containsKey(userId)) {
            scoreChange = voteType == VoteType.UPVOTE? 2: -2;
        }
        else {
            scoreChange = voteType == VoteType.UPVOTE? 1: -1;
        }
        voters.put(userId, voteType);
        votesCount.addAndGet(scoreChange);
        EventType eventType = EventType.UPVOTE_QUESTION;
        if(this instanceof Question) {
            eventType = voteType == VoteType.UPVOTE ? EventType.UPVOTE_QUESTION: EventType.DOWNVOTE_QUESTION;
        }
        else {
            eventType = voteType == VoteType.UPVOTE ? EventType.UPVOTE_ANSWER: EventType.DOWNVOTE_ANSWER;
        }
        notifyObservers(new Event(eventType, user, this));
    }
}

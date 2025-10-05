package pub_sub.entities;

import pub_sub.observer.Subscriber;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;

public class Topic {
    private final String name;
    private final ExecutorService deliveryExecutor;
    private final Set<Subscriber> subscribers;

    public Topic(String name, ExecutorService deliveryExecutor) {
        this.name = name;
        this.deliveryExecutor = deliveryExecutor;
        this.subscribers = new CopyOnWriteArraySet<>();
    }

    public String getName() {
        return name;
    }
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    public void broadcast(Message message) {
        for(Subscriber subscriber: subscribers) {
            deliveryExecutor.submit(() -> {
                try{
                    subscriber.onMessage(message);
                }
                catch (Exception e) {
                    System.err.println("Error delivering message to subscriber " + subscriber.getId() + ": " + e.getMessage());
                }
            });
        }
    }
}

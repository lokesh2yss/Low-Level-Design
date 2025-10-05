package pub_sub.observer;

import pub_sub.entities.Message;

public interface Subscriber {
    String getId();
    void onMessage(Message message);
}

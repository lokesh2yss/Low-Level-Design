package stack_overflow.entities.interfaces;

import stack_overflow.entities.Event;

public interface PostObserver {
    void onPostEvent(Event event);
}

package amazon.observer;

import amazon.entities.Order;

public interface OrderObserver {
    void update(Order order);
}

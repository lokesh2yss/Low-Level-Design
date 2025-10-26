package restaurant_management_system.observer;

import restaurant_management_system.entities.OrderItem;

public interface OrderObserver {
    void update(OrderItem item);
}

package restaurant_management_system.state;

import restaurant_management_system.entities.OrderItem;

public interface OrderItemState {
    void next(OrderItem item);
    void prev(OrderItem item);
    String getStatus();
}

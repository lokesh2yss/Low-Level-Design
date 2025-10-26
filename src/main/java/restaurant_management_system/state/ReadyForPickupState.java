package restaurant_management_system.state;

import restaurant_management_system.entities.OrderItem;

public class ReadyForPickupState implements OrderItemState{
    @Override
    public void next(OrderItem item) {
        // This is the key state. When it transitions, it notifies observers.
        item.notifyObservers();
    }

    @Override
    public void prev(OrderItem item) {
        item.setState(new PreparingState());
    }

    @Override
    public String getStatus() {
        return "READY_FOR_PICKUP";
    }
}

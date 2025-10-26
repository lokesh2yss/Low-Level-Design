package restaurant_management_system.state;

import restaurant_management_system.entities.OrderItem;

public class PreparingState implements OrderItemState{
    @Override
    public void next(OrderItem item) {
        item.setState(new ReadyForPickupState());
    }

    @Override
    public void prev(OrderItem item) {
        item.setState(new PreparingState());
    }

    @Override
    public String getStatus() {
        return "PREPARING";
    }
}

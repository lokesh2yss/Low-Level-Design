package restaurant_management_system.state;

import restaurant_management_system.entities.OrderItem;

public class ServedState implements OrderItemState{
    @Override
    public void next(OrderItem item) {
        System.out.println("This is the final state");
    }

    @Override
    public void prev(OrderItem item) {
        System.out.println("Cannot revert served state");
    }

    @Override
    public String getStatus() {
        return "SERVED";
    }
}

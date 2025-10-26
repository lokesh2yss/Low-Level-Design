package restaurant_management_system.state;

import restaurant_management_system.entities.OrderItem;

public class OrderedState implements OrderItemState{
    @Override
    public void next(OrderItem item) {
        item.setState(new PreparingState());
    }

    @Override
    public void prev(OrderItem item) {
        System.out.println("This is the initial state.");
    }

    @Override
    public String getStatus() {
        return "ORDERED";
    }
}

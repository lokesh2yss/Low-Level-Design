package amazon.state;

import amazon.entities.Order;
import amazon.enums.OrderStatus;

public class ShippedState implements OrderState{
    @Override
    public void ship(Order order) {
        System.out.println("Order is already shipped");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Delivering order #"+ order.getId());
        order.setStatus(OrderStatus.DELIVERED);
        order.setCurrentState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel a shipped order.");
    }
}

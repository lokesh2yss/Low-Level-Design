package amazon.state;

import amazon.entities.Order;

public class DeliveredState implements OrderState{
    @Override
    public void ship(Order order) {
        System.out.println("Order is already delivered");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order is already delivered");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel an already delivered order");
    }
}

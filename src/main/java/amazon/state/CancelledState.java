package amazon.state;

import amazon.entities.Order;

public class CancelledState implements OrderState{
    @Override
    public void ship(Order order) {
        System.out.println("Order is cancelled");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order is canceled");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order is already cancelled");
    }
}

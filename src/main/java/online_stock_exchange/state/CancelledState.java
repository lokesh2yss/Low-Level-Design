package online_stock_exchange.state;

import online_stock_exchange.entities.Order;

public class CancelledState implements OrderState{
    @Override
    public void handle(Order order) {
        System.out.println("Order is cancelled.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order is already cancelled.");
    }
}

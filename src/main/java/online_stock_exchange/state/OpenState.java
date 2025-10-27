package online_stock_exchange.state;

import online_stock_exchange.entities.Order;
import online_stock_exchange.enums.OrderStatus;

public class OpenState implements OrderState{
    @Override
    public void handle(Order order) {
        System.out.println("Order is open and waiting for execution.");
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        order.setState(new CancelledState());
    }
}

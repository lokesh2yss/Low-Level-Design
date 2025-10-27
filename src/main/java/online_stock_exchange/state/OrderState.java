package online_stock_exchange.state;

import online_stock_exchange.entities.Order;

public interface OrderState {
    void handle(Order order);
    void cancel(Order order);
}

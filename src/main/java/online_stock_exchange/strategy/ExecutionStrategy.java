package online_stock_exchange.strategy;

import online_stock_exchange.entities.Order;

public interface ExecutionStrategy {
    boolean canExecute(Order order, double marketPrice);
}

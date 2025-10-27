package online_stock_exchange.strategy;

import online_stock_exchange.entities.Order;

public class MarketOrderStrategy implements ExecutionStrategy{
    @Override
    public boolean canExecute(Order order, double marketPrice) {
        return true;
    }
}

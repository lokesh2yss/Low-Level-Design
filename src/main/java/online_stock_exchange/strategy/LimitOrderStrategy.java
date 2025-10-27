package online_stock_exchange.strategy;

import online_stock_exchange.entities.Order;
import online_stock_exchange.enums.TransactionType;

public class LimitOrderStrategy implements ExecutionStrategy{
    private final TransactionType type;
    public LimitOrderStrategy(TransactionType type) {
        this.type = type;
    }
    @Override
    public boolean canExecute(Order order, double marketPrice) {
        if(type == TransactionType.BUY) {
            // Buy if market price is less than or equal to limit price
            return order.getPrice() >= marketPrice;
        }
        else {
            // SELL
            // Sell if market price is greater than or equal to limit price
            return marketPrice >= order.getPrice();
        }
    }
}

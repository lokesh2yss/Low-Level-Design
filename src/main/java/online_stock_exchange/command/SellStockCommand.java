package online_stock_exchange.command;

import online_stock_exchange.StockExchange;
import online_stock_exchange.entities.Account;
import online_stock_exchange.entities.Order;
import online_stock_exchange.exceptions.InsufficientStockException;

public class SellStockCommand implements OrderCommand{
    private final Order order;
    private final Account account;
    private final StockExchange stockExchange;
    public SellStockCommand(Order order, Account account) {
        this.order = order;
        this.account = account;
        this.stockExchange = StockExchange.getInstance();
    }
    @Override
    public void execute() {
        if(order.getQuantity() > account.getStockQuantity(order.getStock().getSymbol())) {
            throw new InsufficientStockException("Insufficient Stock in account. Cannot place order");
        }
        System.out.printf("Placing SELL order %s for %d shares of %s.%n", order.getOrderId(), order.getQuantity(), order.getStock());
        stockExchange.placeSellOrder(order);
    }
}

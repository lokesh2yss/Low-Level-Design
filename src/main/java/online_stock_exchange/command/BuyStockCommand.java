package online_stock_exchange.command;


import online_stock_exchange.StockExchange;
import online_stock_exchange.entities.Account;
import online_stock_exchange.entities.Order;
import online_stock_exchange.enums.OrderType;
import online_stock_exchange.exceptions.InsufficientFundsException;

public class BuyStockCommand implements OrderCommand {
    private final Order order;
    private final Account account;
    private final StockExchange stockExchange;

    public BuyStockCommand(Order order, Account account) {
        this.order = order;
        this.account = account;
        this.stockExchange = StockExchange.getInstance();
    }
    @Override
    public void execute() {
        // For market order, we can't pre-check funds perfectly.
        // For limit order, we can pre-authorize the amount.
        double estimatedCost = order.getPrice()*order.getQuantity();
        if(order.getType() == OrderType.LIMIT && estimatedCost > account.getBalance()) {
            throw new InsufficientFundsException("Cannot place order. Insufficient funds in the account");
        }
        System.out.printf("Placing BUY order %s for %d shares of %s.%n", order.getOrderId(), order.getQuantity(), order.getStock());
        stockExchange.placeBuyOrder(order);
    }
}

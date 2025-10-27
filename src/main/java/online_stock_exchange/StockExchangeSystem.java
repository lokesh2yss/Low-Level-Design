package online_stock_exchange;

import online_stock_exchange.command.BuyStockCommand;
import online_stock_exchange.command.OrderCommand;
import online_stock_exchange.command.SellStockCommand;
import online_stock_exchange.entities.Order;
import online_stock_exchange.entities.User;
import online_stock_exchange.observer.Stock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockExchangeSystem {
    private static volatile StockExchangeSystem instance;
    private final Map<String, User> users;
    private final Map<String, Stock> stocks;

    private StockExchangeSystem() {
        this.users = new ConcurrentHashMap<>();
        this.stocks = new ConcurrentHashMap<>();
    }

    public static StockExchangeSystem getInstance() {
        if(instance == null) {
            synchronized (StockExchangeSystem.class) {
                if(instance == null) {
                    instance = new StockExchangeSystem();
                }
            }
        }
        return instance;
    }
    public User registerUser(String name, double initialAmount) {
        User user = new User(name, initialAmount);
        users.put(user.getUserId(), user);
        return user;
    }
    public Stock addStock(String symbol, double initialPrice) {
        Stock stock = new Stock(symbol, initialPrice);
        stocks.put(stock.getSymbol(), stock);
        return stock;
    }
    public void placeBuyOrder(Order order) {
        User user = order.getUser();
        OrderCommand command = new BuyStockCommand(order, user.getAccount());
        command.execute();
    }
    public void placeSellOrder(Order order) {
        User user = order.getUser();
        OrderCommand command = new SellStockCommand(order, user.getAccount());
        command.execute();
    }
    public void cancelOrder(Order order) {
        order.cancel();
    }
}

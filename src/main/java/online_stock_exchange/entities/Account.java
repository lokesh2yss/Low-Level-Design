package online_stock_exchange.entities;

import online_stock_exchange.exceptions.InsufficientFundsException;
import online_stock_exchange.exceptions.InsufficientStockException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Account {
    private double balance;
    private final Map<String, Integer> portfolio;
    public Account(double initialCash) {
        this.balance = initialCash;
        this.portfolio = new ConcurrentHashMap<>();
    }
    public synchronized void debit(double amount) {
        if(amount > balance) {
            throw new InsufficientFundsException("Insufficient funds to debit " + amount);
        }
        balance -= amount;
    }

    public synchronized void credit(double amount) {
        balance += amount;
    }
    public synchronized void addStock(String symbol, int quantity) {
        portfolio.merge(symbol, quantity, Integer::sum);
    }
    public synchronized void removeStock(String symbol, int quantity) {
        int existingQty = portfolio.get(symbol);
        if(existingQty < quantity) {
            throw new InsufficientStockException("Insufficient stocks in the account");
        }
        portfolio.put(symbol, existingQty - quantity);
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }
    public int getStockQuantity(String symbol) {
        return portfolio.getOrDefault(symbol, 0);
    }
}

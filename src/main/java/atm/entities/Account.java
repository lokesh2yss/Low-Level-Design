package atm.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Account {
    private final String accountNumber;
    private final Map<String, Card> cards;
    private double balance;
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.cards = new HashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public double getBalance() {
        return balance;
    }
    public synchronized void depositCash(double amount) {
        if(amount <=0) return;
        this.balance += amount;
    }
    public synchronized boolean withdrawCash(double amount) {
        if(amount <= 0) return false;
        if(balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}

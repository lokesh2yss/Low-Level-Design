package atm.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();

    public BankService() {
        // Create sample accounts and cards
        Account account1 = createAccount("1234567890", 1000.0);
        Card card1 = createCard("1234-5678-9012-3456", "1234");
        linkCardToAccount(card1, account1);

        Account account2 = createAccount("9876543210", 500.0);
        Card card2 = createCard("9876-5432-1098-7654", "4321");
        linkCardToAccount(card2, account2);
    }
    public Account createAccount(String accountNumber, double balance) {
        Account account = new Account(accountNumber, balance);
        accounts.put(accountNumber, account);
        return account;
    }
    public Card createCard(String cardNumber, String pin) {
        Card card = new Card(cardNumber, pin);
        cards.put(cardNumber, card);
        return card;
    }
    public void linkCardToAccount(Card card, Account account) {
        cardAccountMap.put(card, account);
        account.getCards().put(card.getCardNumber(), card);
    }
    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }
    public double getBalance(Card card) {
        Account linkedAccount = cardAccountMap.get(card);
        return linkedAccount.getBalance();
    }
    public boolean authenticate(Card card, String pin) {
        return card.getPin().equals(pin);
    }
    public Card authenticate(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }
    public void depositMoney(Card card, double amount) {
        Account linkedAccount = cardAccountMap.get(card);
        linkedAccount.depositCash(amount);
    }
    public void withdrawMoney(Card card, double amount) {
        Account linkedAccount = cardAccountMap.get(card);
        linkedAccount.withdrawCash(amount);
    }
}

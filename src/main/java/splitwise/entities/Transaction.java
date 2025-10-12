package splitwise.entities;

public class Transaction {
    private final User from;
    private final User to;
    private final double amount;

    public Transaction(User from, User user, double amount) {
        this.from = from;
        to = user;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return from.getName() + " should pay " + to.getName() + " $" + String.format("%.2f", amount);
    }
}

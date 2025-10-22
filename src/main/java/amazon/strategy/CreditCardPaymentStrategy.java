package amazon.strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private final String cardNumber;

    public CreditCardPaymentStrategy(String ccNumber) {
        this.cardNumber = ccNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("Processing credit card payment of $%.2f with card %s.%n", amount, cardNumber);
        return true;
    }
}

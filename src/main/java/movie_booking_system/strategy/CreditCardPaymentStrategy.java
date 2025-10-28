package movie_booking_system.strategy;

import movie_booking_system.entities.Payment;
import movie_booking_system.enums.PaymentStatus;

import java.util.UUID;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private final String cardNumber;
    private final String cvv;

    public CreditCardPaymentStrategy(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public Payment pay(double amount) {
        System.out.printf("Processing credit card payment of $%.2f%n", amount);
        boolean paymentSuccess = Math.random() > 0.05; // 95% success rate
        return new Payment(amount,
               paymentSuccess? PaymentStatus.SUCCESS: PaymentStatus.FAILURE,
                "TXN_"+UUID.randomUUID());
    }
}

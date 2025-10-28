package movie_booking_system.entities;

import movie_booking_system.enums.PaymentStatus;

import java.util.UUID;

public class Payment {
    private final String id;
    private final double amount;
    private final PaymentStatus status;
    private final String transactionId;

    public Payment(double amount, PaymentStatus status, String transactionId) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

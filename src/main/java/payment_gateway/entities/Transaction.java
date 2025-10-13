package payment_gateway.entities;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.enums.PaymentStatus;

import java.time.LocalDateTime;

public class Transaction {
    private final String id;
    private final PaymentRequest request;
    private PaymentStatus status;
    private final LocalDateTime timestamp;

    public Transaction(PaymentRequest request) {
        this.id = request.getTransactionId();
        this.request = request;
        this.status = PaymentStatus.INITIATED;
        this.timestamp = LocalDateTime.now();
    }
    public void setStatus(PaymentStatus paymentStatus) {
        this.status = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public PaymentRequest getRequest() {
        return request;
    }
}

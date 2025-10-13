package payment_gateway.strategy;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.entities.PaymentResponse;

public interface PaymentProcessor {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}

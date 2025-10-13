package payment_gateway.strategy;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.entities.PaymentResponse;
import payment_gateway.enums.PaymentStatus;

public abstract class AbstractPaymentProcessor implements PaymentProcessor {
    private static final int MAX_RETRIES = 3;

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        int attempts = 0;
        PaymentResponse response;
        do {
            response = doProcess(paymentRequest);
            attempts++;
        }
        while (response.getStatus() == PaymentStatus.FAILED && attempts < MAX_RETRIES);
        return response;
    }
    protected abstract PaymentResponse doProcess(PaymentRequest request);
}

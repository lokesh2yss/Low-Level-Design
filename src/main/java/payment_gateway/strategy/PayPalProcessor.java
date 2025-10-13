package payment_gateway.strategy;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.entities.PaymentResponse;
import payment_gateway.enums.PaymentStatus;

public class PayPalProcessor extends AbstractPaymentProcessor{
    @Override
    protected PaymentResponse doProcess(PaymentRequest request) {
        System.out.println("Redirecting to PayPal for transaction " + request.getTransactionId());
        // Simulate PayPal API interaction
        return new PaymentResponse(PaymentStatus.SUCCESSFUL, "Paypal payment successful.");
    }
}

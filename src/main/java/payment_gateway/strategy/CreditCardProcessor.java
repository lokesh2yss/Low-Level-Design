package payment_gateway.strategy;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.entities.PaymentResponse;
import payment_gateway.enums.PaymentStatus;

public class CreditCardProcessor extends AbstractPaymentProcessor{
    @Override
    protected PaymentResponse doProcess(PaymentRequest request) {
        System.out.println("Processing credit card payment of amount " + request.getAmount() + " " + request.getCurrency());
        // Simulate interaction with Visa/Mastercard network
        return new PaymentResponse(PaymentStatus.SUCCESSFUL, "Credit Card payment successful.");
    }
}

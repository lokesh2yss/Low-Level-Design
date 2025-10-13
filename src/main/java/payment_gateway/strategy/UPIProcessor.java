package payment_gateway.strategy;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.entities.PaymentResponse;
import payment_gateway.enums.PaymentStatus;

public class UPIProcessor extends AbstractPaymentProcessor{
    @Override
    protected PaymentResponse doProcess(PaymentRequest request) {
        System.out.println("Processing UPI payment of " + request.getAmount() + " " + request.getCurrency());
        return new PaymentResponse(PaymentStatus.SUCCESSFUL, "UPI payment successful.");

    }
}

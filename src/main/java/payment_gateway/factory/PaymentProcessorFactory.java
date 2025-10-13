package payment_gateway.factory;

import payment_gateway.enums.PaymentMethod;
import payment_gateway.strategy.CreditCardProcessor;
import payment_gateway.strategy.PayPalProcessor;
import payment_gateway.strategy.PaymentProcessor;
import payment_gateway.strategy.UPIProcessor;

public class PaymentProcessorFactory {
    public static PaymentProcessor getProcessor(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CREDIT_CARD -> new CreditCardProcessor();
            case PAYPAL -> new PayPalProcessor();
            case UPI -> new UPIProcessor();
            default -> throw new IllegalArgumentException("Payment method not supported");
        };
    }
}

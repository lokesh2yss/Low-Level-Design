package payment_gateway.builder;

import payment_gateway.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public class PaymentRequest {
    private final String transactionId;
    private final String payerId;
    private final double amount;
    private final String currency;
    private final PaymentMethod paymentMethod;
    private final Map<String, String> paymentDetails;

    public PaymentRequest(Builder builder) {
        this.transactionId = UUID.randomUUID().toString();
        this.payerId = builder.payerId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDetails = builder.paymentDetails;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
    public static class Builder {
        private String payerId;
        private double amount;
        private String currency;
        private PaymentMethod paymentMethod;
        private Map<String, String> paymentDetails;

        public Builder payerId(String payerId) {
            this.payerId = payerId;
            return this;
        }
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }
        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }
        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        public Builder paymentDetails(Map<String,String> paymentDetails) {
            this.paymentDetails = paymentDetails;
            return this;
        }
        public PaymentRequest build() {
            return new PaymentRequest(this);
        }

    }
}

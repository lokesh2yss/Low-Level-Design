package payment_gateway.service;

import payment_gateway.builder.PaymentRequest;
import payment_gateway.entities.PaymentResponse;
import payment_gateway.entities.Transaction;
import payment_gateway.enums.PaymentStatus;
import payment_gateway.factory.PaymentProcessorFactory;
import payment_gateway.observer.PaymentObserver;
import payment_gateway.strategy.PaymentProcessor;

import java.util.ArrayList;
import java.util.List;

public class PaymentGatewayService {
    private static PaymentGatewayService instance;
    private final List<PaymentObserver> observers = new ArrayList<>();

    private PaymentGatewayService() {}
    public static synchronized PaymentGatewayService getInstance() {
        if(instance == null) {
            instance = new PaymentGatewayService();
        }
        return instance;
    }
    public void addObserver(PaymentObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(PaymentObserver observer) {
        this.observers.remove(observer);
    }
    public void notifyObservers(Transaction transaction) {
        for(PaymentObserver observer: observers) {
            observer.onTransactionUpdate(transaction);
        }
    }
    public Transaction processPayment(PaymentRequest request) {
        Transaction transaction = new Transaction(request);
        try {
            PaymentProcessor paymentProcessor = PaymentProcessorFactory.getProcessor(request.getPaymentMethod());
            PaymentResponse response = paymentProcessor.processPayment(request);
            transaction.setStatus(response.getStatus());
        }
        catch (Exception e) {
            System.err.println("Payment processing failed: " + e.getMessage());
            transaction.setStatus(PaymentStatus.FAILED);
        }
        notifyObservers(transaction);
        return transaction;
    }
}

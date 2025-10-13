package payment_gateway.observer;

import payment_gateway.entities.Transaction;

public interface PaymentObserver {
    void onTransactionUpdate(Transaction transaction);
}

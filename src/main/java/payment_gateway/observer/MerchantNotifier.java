package payment_gateway.observer;

import payment_gateway.entities.Transaction;

public class MerchantNotifier implements PaymentObserver{
    @Override
    public void onTransactionUpdate(Transaction transaction) {
        System.out.println("--- MERCHANT NOTIFICATION ---");
        System.out.println("Transaction " + transaction.getId() + " status updated to: " + transaction.getStatus());
        System.out.println("-----------------------------");
    }
}

package inventory_management_system.service;

import inventory_management_system.entities.Transaction;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AuditService {
    private static final AuditService instance = new AuditService();
    private final List<Transaction> transactionLog;
    private AuditService() {
        transactionLog = new CopyOnWriteArrayList<>();
    }
    public static AuditService getInstance() {
        return instance;
    }
    public void log(Transaction transaction) {
        this.transactionLog.add(transaction);
    }
    public void printAuditLog() {
        System.out.println("\n--- Audit Log ---");
        transactionLog.forEach(System.out::println);
        System.out.println("-----------------");
    }
}

package inventory_management_system.entities;

import inventory_management_system.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final LocalDateTime timestamp;
    private final String productId;
    private final int warehouseId;
    private final int quantityChange;
    private final TransactionType type;

    public Transaction(String productId, int warehouseId, int quantityChange, TransactionType type) {
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantityChange = quantityChange;
        this.type = type;
    }
    @Override
    public String toString() {
        return String.format("Transaction [ID=%s, Time=%s, Warehouse=%d, Product=%s, Type=%s, QtyChange=%d]",
                transactionId, timestamp, warehouseId, productId, type, quantityChange);
    }
}

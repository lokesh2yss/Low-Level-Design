package inventory_management_system.entities;

import inventory_management_system.observer.StockObserver;

import java.util.ArrayList;
import java.util.List;

public class StockItem {
    private final Product product;
    private int quantity;
    private final int threshold;
    private final int warehouseId;
    private List<StockObserver> observers;

    public StockItem(Product product, int quantity, int threshold, int warehouseId) {
        this.product = product;
        this.quantity = quantity;
        this.threshold = threshold;
        this.warehouseId = warehouseId;
        this.observers = new ArrayList<>();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public Product getProduct() {
        return product;
    }
    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }
    // The critical section for updates. synchronized ensures thread-safety.
    public synchronized boolean updateStock(int quantityChange) {
        if (quantity + quantityChange < 0) {
            System.err.println("Cannot remove more stock than available. " +
                    "Available: " + this.quantity + ", Attempted to remove: " + (-quantityChange));
            return false;
        }
        this.quantity += quantityChange;
        System.out.printf("Stock updated for %s in Warehouse %d. New quantity: %d\n",
                product.getName(), warehouseId, this.quantity);
        notifyObservers();
        return true;
    }
    public void notifyObservers() {
        for(StockObserver observer: observers) {
            observer.onStockUpdate(this);
        }
    }
}

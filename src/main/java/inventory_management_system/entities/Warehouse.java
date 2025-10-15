package inventory_management_system.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {
    private final int warehouseId;
    private final String location;
    private final Map<String, StockItem> stockItems;


    public Warehouse(int warehouseId, String location) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.stockItems = new ConcurrentHashMap<>();
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public String getLocation() {
        return location;
    }
    public void addProductStock(StockItem stockItem) {
        stockItems.put(stockItem.getProduct().getProductId(), stockItem);
    }
    public boolean updateStock(String productId, int quantityChange) {
        StockItem stockItem = stockItems.get(productId);
        if(stockItem != null) {
            return stockItem.updateStock(quantityChange);
        }
        else {
            System.err.println("Error: Product " + productId + " not found in warehouse " + warehouseId);
        }
        return false;
    }
    public int getStockLevel(String productId) {
        StockItem stockItem = stockItems.get(productId);
        return stockItem == null? 0 : stockItem.getQuantity();
    }
    public void printInventory() {
        System.out.println("--- Inventory for Warehouse " + warehouseId + " (" + location + ") ---");
        if (stockItems.isEmpty()) {
            System.out.println("Warehouse is empty.");
            return;
        }
        stockItems.values().forEach(item ->
                System.out.printf("Product: %s (%s), Quantity: %d\n",
                        item.getProduct().getName(), item.getProduct().getProductId(), item.getQuantity())
        );
        System.out.println("-------------------------------------------------");
    }
}

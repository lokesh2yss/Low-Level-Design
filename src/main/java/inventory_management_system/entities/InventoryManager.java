package inventory_management_system.entities;

import inventory_management_system.enums.TransactionType;
import inventory_management_system.observer.LowStockAlertObserver;
import inventory_management_system.service.AuditService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryManager {
    private static final InventoryManager instance = new InventoryManager();
    private final Map<String, Product> products;
    private final Map<Integer, Warehouse> warehouses;
    private final AuditService auditService;

    private InventoryManager() {
        this.products = new ConcurrentHashMap<>();
        this.warehouses = new ConcurrentHashMap<>();
        this.auditService = AuditService.getInstance();
    }
    public static InventoryManager getInstance() {
        return instance;
    }
    public Warehouse addWarehouse(int warehouseId, String location) {
        Warehouse warehouse = new Warehouse(warehouseId, location);
        warehouses.put(warehouseId, warehouse);
        return warehouse;
    }
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }
    public void addProductToWarehouse(String productId, int warehouseId, int initialQuantity, int threshold) {
        Product product = products.get(productId);
        Warehouse warehouse = warehouses.get(warehouseId);
        if (warehouse == null || product == null) {
            System.err.println("Warehouse or product not found");
            return;
        }
        StockItem stockItem = new StockItem(product, initialQuantity, threshold, warehouseId);
        stockItem.addObserver(new LowStockAlertObserver());
        warehouse.addProductStock(stockItem);
        auditService.log(new Transaction(productId, warehouseId, initialQuantity, TransactionType.INITIAL_STOCK));
    }
    private void updateStock(String productId, int warehouseId, int quantityChange) {
        Product product = products.get(productId);
        Warehouse warehouse = warehouses.get(warehouseId);
        if(product == null || warehouse == null) {
            System.err.println("Product or Warehouse not found");
            return;
        }
        warehouse.updateStock(productId, quantityChange);
        TransactionType type = quantityChange > 0? TransactionType.ADD: TransactionType.REMOVE;
        auditService.log(new Transaction(productId, warehouseId, quantityChange, type));
    }
    public void addStock(int warehouseId, String productId, int quantity) {
        updateStock(productId,warehouseId, quantity);
    }

    public void removeStock(int warehouseId, String productId, int quantity) {
        updateStock(productId,warehouseId, -quantity);
    }
    public void viewInventory(int warehouseId) {
        Warehouse warehouse = warehouses.get(warehouseId);
        if (warehouse != null) {
            warehouse.printInventory();
        } else {
            System.err.println("Warehouse with ID " + warehouseId + " not found.");
        }
    }

    public void viewAuditLog() {
        auditService.printAuditLog();
    }
}

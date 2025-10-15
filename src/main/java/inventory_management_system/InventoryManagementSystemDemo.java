package inventory_management_system;

import inventory_management_system.entities.InventoryManager;
import inventory_management_system.entities.Product;
import inventory_management_system.entities.Warehouse;
import inventory_management_system.factory.ProductFactory;

public class InventoryManagementSystemDemo {
    public static void main(String[] args) {
// Get the singleton instance of the InventoryManager
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // 1. Setup: Add warehouses and products
        Warehouse warehouse1 = inventoryManager.addWarehouse(1, "New York");
        Warehouse warehouse2 = inventoryManager.addWarehouse(2, "San Francisco");

        Product laptop = ProductFactory.createProduct("P001", "Dell XPS 15", "A high-performance laptop");
        Product mouse = ProductFactory.createProduct("P002", "Logitech MX Master 3", "An ergonomic wireless mouse");

        inventoryManager.addProduct(laptop);
        inventoryManager.addProduct(mouse);

        // 2. Add initial stock to warehouses
        System.out.println("--- Initializing Stock ---");
        inventoryManager.addProductToWarehouse(laptop.getProductId(), warehouse1.getWarehouseId(), 10, 5); // 10 laptops in NY, threshold 5
        inventoryManager.addProductToWarehouse(mouse.getProductId(), warehouse1.getWarehouseId(), 50, 20);  // 50 mice in NY, threshold 20
        inventoryManager.addProductToWarehouse(laptop.getProductId(), warehouse2.getWarehouseId(), 8, 3);   // 8 laptops in SF, threshold 3
        System.out.println();

        // 3. View initial inventory
        inventoryManager.viewInventory(warehouse1.getWarehouseId());
        inventoryManager.viewInventory(warehouse2.getWarehouseId());

        // 4. Perform stock operations
        System.out.println("\n--- Performing Stock Operations ---");
        inventoryManager.addStock(warehouse1.getWarehouseId(), laptop.getProductId(), 5); // Add 5 laptops to NY
        inventoryManager.removeStock(warehouse1.getWarehouseId(), mouse.getProductId(), 35); // Remove 35 mice from NY -> should trigger alert
        inventoryManager.removeStock(warehouse2.getWarehouseId(), laptop.getProductId(), 6); // Remove 6 laptops from SF -> should trigger alert

        // 5. Demonstrate error case: removing too much stock
        System.out.println("\n--- Demonstrating Insufficient Stock Error ---");
        inventoryManager.removeStock(warehouse2.getWarehouseId(), laptop.getProductId(), 100); // Fails, only 2 left
        System.out.println();

        // 6. View final inventory
        System.out.println("\n--- Final Inventory Status ---");
        inventoryManager.viewInventory(warehouse1.getWarehouseId());
        inventoryManager.viewInventory(warehouse2.getWarehouseId());

        // 7. View the full audit log
        inventoryManager.viewAuditLog();

    }
}

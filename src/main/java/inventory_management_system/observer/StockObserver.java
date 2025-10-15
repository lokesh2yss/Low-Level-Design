package inventory_management_system.observer;

import inventory_management_system.entities.StockItem;

public interface StockObserver {
    void onStockUpdate(StockItem stockItem);
}

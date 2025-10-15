package inventory_management_system.observer;

import inventory_management_system.entities.StockItem;

public class LowStockAlertObserver implements StockObserver{
    @Override
    public void onStockUpdate(StockItem stockItem) {
        if(stockItem.getQuantity() < stockItem.getThreshold()) {
            System.out.printf("ALERT: Low stock for %s in warehouse %s. Current quantity: %d, Threshold: %d\n",
                    stockItem.getProduct().getName(), stockItem.getWarehouseId(),
                    stockItem.getQuantity(), stockItem.getThreshold());
        }
    }
}

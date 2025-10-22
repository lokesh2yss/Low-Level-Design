package amazon.services;

import amazon.entities.OrderLineItem;
import amazon.entities.Product;
import amazon.exceptions.OutOfStockException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {
    private final Map<String, Integer> stock; // productId -> quantity
    public InventoryService() {
        this.stock = new ConcurrentHashMap<>();
    }
    public void addStock(Product product, int quantity) {
        stock.put(product.getId(), stock.getOrDefault(product.getId(), 0)+ quantity);
    }
    public synchronized void updateStockForOrder(List<OrderLineItem> items) {
        // First, check if all items are in stock
        for(OrderLineItem item: items) {
            if(stock.getOrDefault(item.getProductId(), 0) < item.getQuantity()) {
                throw new OutOfStockException("Item+"+item.getProductId()+" is out of stock");
            }
        }
        for(OrderLineItem item: items) {
            stock.put(item.getProductId(), stock.get(item.getProductId()) - item.getQuantity());
        }
    }
}

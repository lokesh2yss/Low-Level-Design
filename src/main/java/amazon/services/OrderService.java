package amazon.services;

import amazon.entities.Customer;
import amazon.entities.Order;
import amazon.entities.OrderLineItem;
import amazon.entities.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final InventoryService inventoryService;

    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    public Order createOrder(Customer customer, ShoppingCart cart) {
        List<OrderLineItem> itemList = cart.getCartItems().values()
                .stream()
                .map(cartItem -> new OrderLineItem(
                        cartItem.getProduct().getId(),
                        cartItem.getProduct().getName(),
                        cartItem.getQuantity(),
                        cartItem.getProduct().getPrice()))
                .toList();
        inventoryService.updateStockForOrder(itemList);
        return new Order(customer, customer.getShippingAddress(), itemList, cart.calculatePrice());
    }
}

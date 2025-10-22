package amazon.entities;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> cartItems = new HashMap<>();
    public void addItem(Product product, int quantity) {
        if(!cartItems.containsKey(product.getId())) {
            CartItem item = new CartItem(product, quantity);
            cartItems.put(product.getId(), item);
        }
        else {
            CartItem item = cartItems.get(product.getId());
            item.incrementQuantity(quantity);
        }
    }
    public void removeItem(String productId) {
        cartItems.remove(productId);
    }
    public double calculatePrice() {
        return cartItems.values().stream()
                .mapToDouble(item -> item.getPrice()* item.getQuantity())
                .sum();
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }
    public void clearCart() {
        cartItems.clear();
    }
}

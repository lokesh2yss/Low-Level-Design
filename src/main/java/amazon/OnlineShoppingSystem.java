package amazon;


import amazon.entities.*;
import amazon.services.InventoryService;
import amazon.services.OrderService;
import amazon.services.PaymentService;
import amazon.services.SearchService;
import amazon.strategy.PaymentStrategy;

import javax.swing.plaf.IconUIResource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineShoppingSystem {
    private static volatile OnlineShoppingSystem instance;
    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Map<String, Customer> customers = new ConcurrentHashMap<>();
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    private final InventoryService inventoryService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final SearchService searchService;

    private OnlineShoppingSystem() {
        this.inventoryService = new InventoryService();
        this.orderService = new OrderService(inventoryService);
        this.paymentService = new PaymentService();
        this.searchService = new SearchService(products.values());
    }

    public static OnlineShoppingSystem getInstance() {
        if(instance == null) {
            synchronized (OnlineShoppingSystem.class) {
                if(instance == null) {
                    instance = new OnlineShoppingSystem();
                }
            }
        }
        return instance;
    }
    public void addProduct(Product product, int initialStock) {
        products.put(product.getId(), product);
        inventoryService.addStock(product, initialStock);
    }
    public Customer registerCustomer(String name, String email, String password, Address address) {
        Customer customer = new Customer(name, email, address, password);
        customers.put(customer.getId(), customer);
        return customer;
    }
    public void addToCart(String customerId, String productId, int quantity ) {
        Customer customer = customers.get(customerId);
        Product product = products.get(productId);
        customer.getAccount().getCart().addItem(product, quantity);
    }
    public ShoppingCart getCustomerCart(String customerId) {
        Customer customer = customers.get(customerId);
        return customer.getAccount().getCart();
    }
    public List<Product> searchProducts(String name) {
        return searchService.searchByName(name);
    }
    public Order placeOrder(String customerId, PaymentStrategy paymentStrategy) {
        Customer customer = customers.get(customerId);
        ShoppingCart cart = customer.getAccount().getCart();
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Cannot place an order with an empty cart.");
            return null;
        }
        boolean paymentSuccess = paymentService.processPayment(paymentStrategy, cart.calculatePrice());
        if(!paymentSuccess) {
            System.out.println("Payment failed! Try again");
            return null;
        }
        try {
            Order order = orderService.createOrder(customer, cart);
            orders.put(order.getId(), order);
            cart.clearCart();
            return order;
        }
        catch (Exception  e) {
            System.err.println("Order placement failed: " + e.getMessage());
            // In a real system, we would trigger a refund here.
            return null;
        }
    }
}

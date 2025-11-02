package online_food_delivery_service.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private Address address;
    private final List<Order> orderHistory = new ArrayList<>();
    public Customer(String name, String phone, Address address) {
        super(name, phone);
        this.address = address;
    }
    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public void onUpdate(Order order) {
        System.out.printf("--- Notification for Customer %s ---\n", getName());
        System.out.printf("  Order %s is now %s.\n", order.getId(), order.getStatus());
        System.out.println("-------------------------------------\n");
    }
}

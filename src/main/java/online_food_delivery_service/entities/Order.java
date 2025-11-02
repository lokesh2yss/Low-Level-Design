package online_food_delivery_service.entities;

import online_food_delivery_service.enums.OrderStatus;
import online_food_delivery_service.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final Customer customer;
    private final Restaurant restaurant;
    private DeliveryAgent deliveryAgent;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final List<OrderObserver> observers = new ArrayList<>();
    private double totalPrice;

    public Order(Customer customer, Restaurant restaurant, List<OrderItem> items) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = items;
        this.status = OrderStatus.PENDING;
        addObserver(restaurant);
        addObserver(customer);
    }
    public void assignDeliveryAgent(DeliveryAgent agent) {
        this.deliveryAgent = agent;
        addObserver(agent);
        agent.setAvailable(false);
    }
    public void setStatus(OrderStatus status) {
        if(this.status != status) {
            this.status = status;
            notifyObservers();
        }
    }
    public boolean cancel() {
        // Only allow cancellation if the order is still in the PENDING state.
        if(status == OrderStatus.PENDING) {
            setStatus(OrderStatus.CANCELLED);
            return true;
        }
        return false;
    }
    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getQuantity()*item.getItem().getPrice())
                .sum();
    }
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for(OrderObserver observer: observers) {
            observer.onUpdate(this);
        }
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}

package amazon.entities;

import amazon.enums.OrderStatus;
import amazon.state.OrderState;
import amazon.state.PlacedState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Order extends Subject {
    private final String id;
    private Customer customer;
    private final Address shippingAddress;
    private final List<OrderLineItem> items;
    private final double totalAmount;
    private final LocalDateTime orderDate;
    private OrderStatus status;
    private OrderState currentState;

    public Order(Customer customer, Address shippingAddress, List<OrderLineItem> items, double totalAmount) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PLACED;
        this.currentState = new PlacedState();
        addObserver(customer);
    }
    public void shipOrder() {
        currentState.ship(this);
    }
    public void deliverOrder() {currentState.deliver(this);}
    public void cancelOrder() {
        currentState.cancel(this);
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        notifyObservers(this);
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
    }

    public List<OrderLineItem> getItems() {
        return items;
    }
}

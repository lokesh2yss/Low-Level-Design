package online_stock_exchange.entities;

import online_stock_exchange.enums.OrderStatus;
import online_stock_exchange.enums.OrderType;
import online_stock_exchange.observer.Stock;
import online_stock_exchange.state.OpenState;
import online_stock_exchange.state.OrderState;
import online_stock_exchange.strategy.ExecutionStrategy;

public class Order {
    private final String orderId;
    private final User user;
    private final Stock stock;
    private final OrderType type;
    private final int quantity;
    private final double price;
    private OrderStatus status;
    private User owner;
    private OrderState currentState;
    private final ExecutionStrategy executionStrategy;

    public Order(String orderId, User user, Stock stock, OrderType type, int quantity, double price, ExecutionStrategy strategy, User owner) {
        this.orderId = orderId;
        this.user = user;
        this.stock = stock;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.OPEN;
        this.owner = owner;
        this.currentState = new OpenState();
        this.executionStrategy = strategy;
    }
    public void cancel() {
        currentState.cancel(this);
    }

    public double getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderType getType() {
        return type;
    }

    public ExecutionStrategy getExecutionStrategy() {
        return executionStrategy;
    }

    public int getQuantity() {
        return quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public User getOwner() {
        return owner;
    }

    public User getUser() {
        return user;
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }
    public void setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
        notifyOwner();
    }
    public void notifyOwner() {
        if(owner != null) {
            owner.orderStatusUpdate(this);
        }
    }
}

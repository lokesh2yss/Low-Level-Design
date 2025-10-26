package restaurant_management_system.entities;

import restaurant_management_system.observer.OrderObserver;
import restaurant_management_system.state.OrderItemState;
import restaurant_management_system.state.OrderedState;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private final MenuItem menuItem;
    private final Order order;
    private OrderItemState state;
    private final List<OrderObserver> observers = new ArrayList<>();

    public OrderItem(MenuItem menuItem, Order order) {
        this.menuItem = menuItem;
        this.order = order;
        this.state = new OrderedState();
    }
    public void changeState(OrderItemState newState) {
        this.state = newState;
        System.out.println("Item '" + menuItem.getName() + "' state changed to: " + newState.getStatus());
    }

    public void nextState() {
        state.next(this);
    }

    public void setState(OrderItemState state) {
        this.state = state;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public Order getOrder() {
        return order;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for(OrderObserver observer: observers) {
            observer.update(this);
        }
    }
}

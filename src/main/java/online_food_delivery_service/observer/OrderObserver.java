package online_food_delivery_service.observer;


import online_food_delivery_service.entities.Order;

public interface OrderObserver {
    void onUpdate(Order order);
}

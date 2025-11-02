package online_food_delivery_service.entities;

import online_food_delivery_service.observer.OrderObserver;

import java.util.UUID;

public abstract class User implements OrderObserver {
    private final String id;
    private String name;
    private String phone;

    protected User(String name, String phone) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package online_food_delivery_service.strategy;

import online_food_delivery_service.entities.Restaurant;

import java.util.List;

public interface RestaurantSearchStrategy {
    List<Restaurant> filter(List<Restaurant> allRestaurant);
}

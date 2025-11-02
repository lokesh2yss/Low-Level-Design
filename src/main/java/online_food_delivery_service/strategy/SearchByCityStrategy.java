package online_food_delivery_service.strategy;

import online_food_delivery_service.entities.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByCityStrategy implements RestaurantSearchStrategy{
    private final String city;

    public SearchByCityStrategy(String city) {
        this.city = city;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurant) {
        return allRestaurant.stream()
                .filter(restaurant -> restaurant.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}

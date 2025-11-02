package online_food_delivery_service.strategy;

import online_food_delivery_service.entities.Address;
import online_food_delivery_service.entities.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByProximityStrategy implements RestaurantSearchStrategy {
    private final Address userLocation;
    private final double maxDistance;

    public SearchByProximityStrategy(Address userLocation, double maxDistance) {
        this.userLocation = userLocation;
        this.maxDistance = maxDistance;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurant) {
        return allRestaurant.stream()
                .filter(restaurant -> restaurant.getAddress().distanceTo(userLocation) <= maxDistance)
                .sorted(Comparator.comparing(r -> userLocation.distanceTo(r.getAddress())))
                .collect(Collectors.toList());
    }
}

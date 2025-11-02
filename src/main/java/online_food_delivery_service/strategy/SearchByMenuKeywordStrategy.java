package online_food_delivery_service.strategy;

import online_food_delivery_service.entities.Restaurant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByMenuKeywordStrategy implements RestaurantSearchStrategy{
    private final String keyword;

    public SearchByMenuKeywordStrategy(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> allRestaurant) {
        return allRestaurant
                .stream()
                .filter(restaurant -> restaurant.getMenu()
                        .getItems().values().stream()
                        .anyMatch(item -> item.getName().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }
}

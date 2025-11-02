package online_food_delivery_service.strategy;

import online_food_delivery_service.entities.Address;
import online_food_delivery_service.entities.DeliveryAgent;
import online_food_delivery_service.entities.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NearestAvailableAgentStrategy implements DeliveryAssignmentStrategy{
    @Override
    public Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents) {
        Address restaurantAddress = order.getRestaurant().getAddress();
        Address customerAddress = order.getCustomer().getAddress();
        return agents.stream()
                .filter(DeliveryAgent::isAvailable)
                .min(Comparator.comparing(agent -> calculateTotalDistance(agent, restaurantAddress, customerAddress)));
    }
    private double calculateTotalDistance(DeliveryAgent agent, Address restaurantAddress, Address customerAddress) {
        double agentToRestaurantDist = agent.getCurrentLocation().distanceTo(restaurantAddress);
        double restaurantToCustomerDist = restaurantAddress.distanceTo(customerAddress);
        return agentToRestaurantDist + restaurantToCustomerDist;    }
}

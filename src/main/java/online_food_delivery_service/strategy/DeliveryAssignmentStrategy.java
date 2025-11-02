package online_food_delivery_service.strategy;

import online_food_delivery_service.entities.DeliveryAgent;
import online_food_delivery_service.entities.Order;

import java.util.List;
import java.util.Optional;

public interface DeliveryAssignmentStrategy {
    Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents);
}

package ride_hailing_service.strategy;

import ride_hailing_service.entities.Location;
import ride_hailing_service.enums.RideType;

import java.util.Map;

public class VehicleBasedPricingStrategy implements PricingStrategy {
    private static final double BASE_FARE = 2.5;
    private static final Map<RideType, Double> RATE_PER_KM = Map.of(
            RideType.SEDAN, 1.5,
            RideType.SUV, 2.0,
            RideType.AUTO, 1.0
    );
    @Override
    public double calculatePrice(Location pickup, Location dropoff, RideType rideType) {
        return BASE_FARE + RATE_PER_KM.get(rideType)*pickup.distanceTo(dropoff);
    }
}

package ride_hailing_service.strategy;

import parking_lot.strategy.FarthestFirstStrategy;
import ride_hailing_service.entities.Location;
import ride_hailing_service.enums.RideType;

public class FlatRatePricingStrategy implements PricingStrategy{
    private static final double BASE_FARE = 5.0;
    private static final double FLAT_RATE = 1.5;
    @Override
    public double calculatePrice(Location pickup, Location dropoff, RideType rideType) {
        return BASE_FARE + pickup.distanceTo(dropoff)* FLAT_RATE;
    }
}

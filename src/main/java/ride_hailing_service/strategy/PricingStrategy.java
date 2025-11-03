package ride_hailing_service.strategy;

import ride_hailing_service.entities.Location;
import ride_hailing_service.enums.RideType;

public interface PricingStrategy {
    double calculatePrice(Location pickup, Location dropoff, RideType rideType);
}

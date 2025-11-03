package ride_hailing_service.strategy;

import ride_hailing_service.entities.Driver;
import ride_hailing_service.entities.Location;
import ride_hailing_service.enums.RideType;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findDrivers(List<Driver> allDrivers, Location pickupLocation, RideType rideType);
}

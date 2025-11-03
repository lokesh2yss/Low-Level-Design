package ride_hailing_service.strategy;

import ride_hailing_service.entities.Driver;
import ride_hailing_service.entities.Location;
import ride_hailing_service.enums.DriverStatus;
import ride_hailing_service.enums.RideType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {
    private static final double MAX_DISTANCE_KM = 5.0; // Max distance to consider a driver "nearby"
    @Override
    public List<Driver> findDrivers(List<Driver> allDrivers, Location pickupLocation, RideType rideType) {
        return allDrivers.stream()
                .filter(driver -> driver.getStatus() == DriverStatus.ONLINE)
                .filter(driver -> driver.getVehicle().getType() == rideType)
                .filter(driver -> driver.getCurrentLocation().distanceTo(pickupLocation) <= MAX_DISTANCE_KM)
                .sorted(Comparator.comparingDouble(driver -> driver.getCurrentLocation().distanceTo(pickupLocation)))
                .collect(Collectors.toList());
    }
}

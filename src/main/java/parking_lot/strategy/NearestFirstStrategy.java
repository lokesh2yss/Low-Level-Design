package parking_lot.strategy;

import parking_lot.entities.ParkingFloor;
import parking_lot.entities.ParkingSpot;
import parking_lot.entities.Vehicle;
import parking_lot.strategy.interfaces.ParkingStrategy;

import java.util.List;
import java.util.Optional;

public class NearestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for(ParkingFloor floor: floors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if(spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}

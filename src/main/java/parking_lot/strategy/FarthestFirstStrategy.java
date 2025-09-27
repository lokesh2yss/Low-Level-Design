package parking_lot.strategy;

import parking_lot.entities.ParkingFloor;
import parking_lot.entities.ParkingSpot;
import parking_lot.entities.Vehicle;
import parking_lot.strategy.interfaces.ParkingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FarthestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        List<ParkingFloor> reversedFloors = new ArrayList<>(floors);
        Collections.reverse(reversedFloors);
        for(ParkingFloor floor: reversedFloors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if(spot.isPresent()) return spot;
        }
        return Optional.empty();
    }
}

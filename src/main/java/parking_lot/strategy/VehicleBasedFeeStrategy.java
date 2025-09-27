package parking_lot.strategy;

import parking_lot.entities.ParkingTicket;
import parking_lot.enums.VehicleSize;
import parking_lot.strategy.interfaces.FeeStrategy;

import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    private static final Map<VehicleSize, Double> HOURLY_RATES = Map.of(VehicleSize.SMALL, 10.0, VehicleSize.MEDIUM, 20.0, VehicleSize.LARGE, 30.0);
    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        long hours = (duration/ (1000 * 60 * 60)) + 1;
        return hours*HOURLY_RATES.get(parkingTicket.getVehicle().getSize());
    }
}

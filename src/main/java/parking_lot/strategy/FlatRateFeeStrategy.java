package parking_lot.strategy;

import parking_lot.entities.ParkingTicket;
import parking_lot.strategy.interfaces.FeeStrategy;

public class FlatRateFeeStrategy implements FeeStrategy {
    private static final double RATE_PER_HOUR = 10.0;
    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        long hours = (duration/(1000 * 60 * 60)) + 1;
        return hours* RATE_PER_HOUR;
    }
}

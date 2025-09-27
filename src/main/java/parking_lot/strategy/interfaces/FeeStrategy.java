package parking_lot.strategy.interfaces;

import parking_lot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}

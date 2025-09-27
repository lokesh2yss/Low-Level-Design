package parking_lot.entities;

import parking_lot.strategy.FlatRateFeeStrategy;
import parking_lot.strategy.interfaces.FeeStrategy;
import parking_lot.strategy.interfaces.ParkingStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets = new ConcurrentHashMap<>();

    private FeeStrategy feeStrategy;
    public ParkingLot() {
        this.feeStrategy = new FlatRateFeeStrategy();
    }

    public static synchronized ParkingLot getInstance() {
        return INSTANCE;
    }
    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setFeeStrategy (FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }
    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        for(ParkingFloor floor: floors) {
            Optional<ParkingSpot> spotOpt = floor.findAvailableSpot(vehicle);
            if(spotOpt.isPresent()) {
                ParkingSpot spot = spotOpt.get();
                spot.parkVehicle(vehicle);
                ParkingTicket ticket = new ParkingTicket(vehicle, spot);
                this.activeTickets.put(vehicle.getLicenseNumber(), ticket);
                return ticket;
            }
        }
        throw new RuntimeException("No available spot for " + vehicle.getSize());
    }
    public synchronized double unParkVehicle(String licenseNumber) {
        ParkingTicket ticket  = activeTickets.remove(licenseNumber);
        if(ticket == null) {
            throw new RuntimeException("Ticket not found");
        }
        ticket.getSpot().unParkVehicle();
        ticket.setExitTimestamp();
        return feeStrategy.calculateFee(ticket);
    }
}

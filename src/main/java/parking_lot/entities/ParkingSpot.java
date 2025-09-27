package parking_lot.entities;

import parking_lot.enums.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private final VehicleSize spotSize;

    private boolean isOccupied;
    private Vehicle parkedVehicle;


    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }
    public String getSpotId() {
        return spotId;
    }
    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }
    public synchronized void unParkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
    public boolean canFitVehicle(Vehicle vehicle) {
        if (isOccupied) return false;

        return switch (vehicle.getSize()) {
            case SMALL -> this.spotSize == VehicleSize.SMALL;
            case MEDIUM -> this.spotSize == VehicleSize.MEDIUM || this.spotSize == VehicleSize.LARGE;
            case LARGE -> this.spotSize == VehicleSize.LARGE;
        };
    }

}

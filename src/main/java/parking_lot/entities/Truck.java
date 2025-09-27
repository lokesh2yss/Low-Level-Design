package parking_lot.entities;

import parking_lot.enums.VehicleSize;

public class Truck extends Vehicle{
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleSize.LARGE);
    }
}

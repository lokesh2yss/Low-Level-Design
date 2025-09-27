package parking_lot.entities;

import parking_lot.enums.VehicleSize;

public class Bike extends Vehicle{
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleSize.SMALL);
    }
}

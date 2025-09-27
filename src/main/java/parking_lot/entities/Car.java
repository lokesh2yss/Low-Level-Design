package parking_lot.entities;

import parking_lot.enums.VehicleSize;

public class Car extends Vehicle{
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleSize.MEDIUM);
    }
}

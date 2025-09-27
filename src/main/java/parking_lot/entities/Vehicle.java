package parking_lot.entities;

import parking_lot.enums.VehicleSize;

public class Vehicle {
    private final String licenseNumber;
    private final VehicleSize size;

    public Vehicle(String licenseNumber, VehicleSize size) {
        this.licenseNumber = licenseNumber;
        this.size = size;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public VehicleSize getSize() {
        return size;
    }
}

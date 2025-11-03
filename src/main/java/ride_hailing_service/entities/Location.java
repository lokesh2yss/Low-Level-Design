package ride_hailing_service.entities;

import java.util.Map;

public class Location {
    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double distanceTo(Location other) {
        double latDiff = this.latitude - other.latitude;
        double longDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff*latDiff + longDiff*longDiff);
    }
    @Override
    public String toString() {
        return "Location(" + latitude + ", " + longitude + ")";
    }
}

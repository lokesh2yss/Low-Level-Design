package online_food_delivery_service.entities;

public class Address {
    private final String street;
    private final String city;
    private final String zipCode;
    private final double latitude;
    private final double longitude;

    public Address(String street, String city, String zipCode, double latitude, double longitude) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public double distanceTo(Address other) {
        double latDiff = this.latitude - other.latitude;
        double longDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff*latDiff + longDiff*longDiff);
    }
    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode + " @(" + latitude + ", " + longitude + ")";
    }
}

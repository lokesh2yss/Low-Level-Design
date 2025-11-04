package ride_hailing_service.entities;

import ride_hailing_service.enums.TripStatus;
import ride_hailing_service.observer.TripObserver;
import ride_hailing_service.state.RequestedState;
import ride_hailing_service.state.TripState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Trip {
    private final String id;
    private final Rider rider;
    private Driver driver;
    private TripState state;
    private TripStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private final Location pickupLocation;
    private final Location dropoffLocation;
    private final double fare;

    private final List<TripObserver> observers = new ArrayList<>();

    private Trip(TripBuilder builder) {
        this.id = builder.id;
        this.rider = builder.rider;
        this.pickupLocation = builder.pickupLocation;
        this.dropoffLocation = builder.dropoffLocation;
        this.fare = builder.fare;
        this.status = TripStatus.REQUESTED;
        this.state = new RequestedState();
    }
    public void addObserver(TripObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.onUpdate(this));
    }
    public void assignDriver(Driver driver) {
        state.assign(this, driver);
        addObserver(driver);
        notifyObservers();
    }
    public void startTrip() {
        state.start(this);
        this.startedAt = LocalDateTime.now();
        notifyObservers();
    }
    public void endTrip() {
        state.end(this);
        this.endedAt = LocalDateTime.now();
        notifyObservers();
    }

    public String getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getFare() {
        return fare;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDropoffLocation() {
        return dropoffLocation;
    }

    public TripState getState() {
        return state;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setState(TripState state) {
        this.state = state;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public static class TripBuilder {
        private final String id;
        private Rider rider;
        private Location pickupLocation;
        private Location dropoffLocation;
        private double fare;
        public TripBuilder() {
            this.id = UUID.randomUUID().toString();
        }
        public TripBuilder withRider(Rider rider) {
            this.rider = rider;
            return this;
        }
        public TripBuilder withPickupLocation(Location pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }
        public TripBuilder withDropoffLocation(Location dropoffLocation) {
            this.dropoffLocation = dropoffLocation;
            return this;
        }
        public TripBuilder withFare(double fare) {
            this.fare = fare;
            return this;
        }
        public Trip build() {
            // Basic validation
            if (rider == null || pickupLocation == null || dropoffLocation == null) {
                throw new IllegalStateException("Rider, pickup, and dropoff locations are required to build a trip.");
            }
            return new Trip(this);
        }
    }
    @Override
    public String toString() {
        return "Trip [id=" + id + ", status=" + status + ", fare=$" + String.format("%.2f", fare) + "]";
    }
}

package ride_hailing_service.state;

import ride_hailing_service.entities.Driver;
import ride_hailing_service.entities.Trip;

public class CompletedState implements TripState {
    @Override
    public void request(Trip trip) {
        System.out.println("The trip is already completed. Cannot be requested");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
        System.out.println("Cannot assign a driver to a completed trip.");
    }

    @Override
    public void start(Trip trip) {
        System.out.println("Trip is already completed. Cannot start it");
    }

    @Override
    public void end(Trip trip) {
        System.out.println("The trip is already completed.");
    }
}

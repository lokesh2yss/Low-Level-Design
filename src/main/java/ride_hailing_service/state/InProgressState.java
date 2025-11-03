package ride_hailing_service.state;

import ride_hailing_service.entities.Driver;
import ride_hailing_service.entities.Trip;
import ride_hailing_service.enums.TripStatus;

public class InProgressState implements TripState{
    @Override
    public void request(Trip trip) {
        System.out.println("Cannot request an in-progress trip");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
        System.out.println("Cannot re-assign an in-progress trip");
    }

    @Override
    public void start(Trip trip) {
        System.out.println("The trip is already started");
    }

    @Override
    public void end(Trip trip) {
        trip.setStatus(TripStatus.COMPLETED);
        trip.setState(new CompletedState());
    }
}

package ride_hailing_service.state;

import ride_hailing_service.entities.Driver;
import ride_hailing_service.entities.Trip;
import ride_hailing_service.enums.TripStatus;

public class RequestedState implements TripState{
    @Override
    public void request(Trip trip) {
        System.out.println("Trip is already in requested state");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
        trip.setDriver(driver);
        trip.setStatus(TripStatus.ASSIGNED);
        trip.setState(new AssignedState());
        System.out.println("Trip "+trip.getId() + " is now assigned to driver :"+driver.getName());
    }

    @Override
    public void start(Trip trip) {
        System.out.println("The trip must be assigned before it can be started");
    }

    @Override
    public void end(Trip trip) {
        System.out.println("Cannot end a trip that has not been assigned a driver.");
    }
}

package ride_hailing_service.state;

import ride_hailing_service.entities.Driver;
import ride_hailing_service.entities.Trip;

public interface TripState {
    void request(Trip trip);
    void assign(Trip trip, Driver driver);
    void start(Trip trip);
    void end(Trip trip);
}

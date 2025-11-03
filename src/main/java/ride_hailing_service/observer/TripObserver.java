package ride_hailing_service.observer;
import ride_hailing_service.entities.Trip;

public interface TripObserver {
    void onUpdate(Trip trip);
}

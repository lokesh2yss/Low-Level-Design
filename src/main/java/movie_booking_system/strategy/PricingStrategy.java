package movie_booking_system.strategy;

import movie_booking_system.entities.Seat;

import java.util.List;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);
}

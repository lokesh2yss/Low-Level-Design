package movie_booking_system.strategy;

import movie_booking_system.entities.Seat;

import java.util.List;

public class WeekdayPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.stream()
                .mapToDouble(seat -> seat.getType().getPrice())
                .sum();
    }
}

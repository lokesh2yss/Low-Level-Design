package movie_booking_system.strategy;

import movie_booking_system.entities.Seat;

import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy{
    private static final double WEEKEND_SURCHARGE = 1.2;
    @Override
    public double calculatePrice(List<Seat> seats) {
        double basePrice =  seats.stream()
                .mapToDouble(seat -> seat.getType().getPrice())
                .sum();
        return basePrice*WEEKEND_SURCHARGE;

    }
}

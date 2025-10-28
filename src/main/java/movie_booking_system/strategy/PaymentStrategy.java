package movie_booking_system.strategy;

import movie_booking_system.entities.Payment;

public interface PaymentStrategy {
    Payment pay(double amount);
}

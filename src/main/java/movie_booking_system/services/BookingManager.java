package movie_booking_system.services;

import movie_booking_system.entities.*;
import movie_booking_system.enums.PaymentStatus;
import movie_booking_system.strategy.PaymentStrategy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingManager {
    private final SeatLockManager seatLockManager;

    public BookingManager(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
    }
    public Optional<Booking> createBooking(User user, Show show, List<Seat> seats, PaymentStrategy paymentStrategy) {
        //1. Lock the seats
        seatLockManager.lockSeats(show, seats, user.getId());

        //2. calculate total price
        double totalPrice = show.getPricingStrategy().calculatePrice(seats);

        //3. Process payment
        Payment payment = paymentStrategy.pay(totalPrice);

        //4. If payment is successful, create the booking
        if(payment.getStatus() == PaymentStatus.SUCCESS) {
            Booking booking = new Booking.BookingBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setUser(user)
                    .setShow(show)
                    .setSeats(seats)
                    .setTotalAmount(totalPrice)
                    .setPayment(payment)
                    .build();

            booking.confirmBooking();
            //clean up the lock map
            seatLockManager.unlockSeats(show, seats, user.getId());
            return Optional.of(booking);
        }
        else {
            System.out.println("Payment failed. Please try again.");
            return Optional.empty();
        }
    }
}

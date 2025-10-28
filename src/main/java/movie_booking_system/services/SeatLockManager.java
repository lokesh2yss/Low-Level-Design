package movie_booking_system.services;

import movie_booking_system.entities.Seat;
import movie_booking_system.entities.Show;
import movie_booking_system.enums.SeatStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SeatLockManager {
    private final Map<Show, Map<Seat, String>> lockedSeats = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final long LOCK_TIMEOUT_MS = 500;

    public void lockSeats(Show show, List<Seat> seats, String userId) {
        synchronized (show) { // Synchronize on the show to ensure atomicity for that specific show
            // Check if any of the requested seats are already locked or booked
            for(Seat seat: seats) {
                if(seat.getStatus() != SeatStatus.AVAILABLE) {
                    System.out.println("Seat " + seat.getId() + " is not available.");
                    return;
                }
            }
            // Lock the seats
            for(Seat seat: seats) {
                seat.setStatus(SeatStatus.LOCKED);
            }
            lockedSeats.computeIfAbsent(show, k -> new ConcurrentHashMap<>());
            for(Seat seat: seats) {
                lockedSeats.get(show).put(seat, userId);
            }
            // Schedule a task to unlock the seats after a timeout
            scheduler.schedule(() -> unlockSeats(show, seats, userId), LOCK_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            System.out.println("Locked seats: " + seats.stream().map(Seat::getId).toList() + " for user " + userId);
        }
    }

    public void unlockSeats(Show show, List<Seat> seats, String userId) {
        synchronized (show) {
            Map<Seat, String> showLocks = lockedSeats.get(show);
            if(showLocks != null) {
                for(Seat seat: seats) {
                    // Only unlock if it's still locked by the same user (prevents race conditions)
                    if(showLocks.containsKey(seat) && showLocks.get(seat).equals(userId)) {
                        showLocks.remove(seat);
                        if(seat.getStatus() == SeatStatus.LOCKED) {
                            seat.setStatus(SeatStatus.AVAILABLE);
                            System.out.println("Unlocked seat: " + seat.getId() + " due to timeout.");
                        }
                        else {
                            showLocks.remove(seat);
                            System.out.println("Unlocked seat: " + seat.getId() + " due to booking completion.");
                        }
                    }
                }
                if(showLocks.isEmpty()) {
                    lockedSeats.remove(show);
                }
            }
        }
    }
    public void shutdown() {
        System.out.println("Shutting down SeatLockProvider scheduler.");
        scheduler.shutdown();
        try {
            if(!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

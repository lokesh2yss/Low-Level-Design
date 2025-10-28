package movie_booking_system.entities;

import java.time.LocalDateTime;
import movie_booking_system.strategy.PricingStrategy;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final PricingStrategy pricingStrategy;

    public Show(String id, Movie movie, Screen screen, LocalDateTime startTime, PricingStrategy pricingStrategy) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.pricingStrategy = pricingStrategy;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}

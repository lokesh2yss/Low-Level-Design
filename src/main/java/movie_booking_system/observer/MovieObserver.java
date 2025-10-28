package movie_booking_system.observer;

import movie_booking_system.entities.Movie;

public interface MovieObserver {
    void update(Movie movie);
}

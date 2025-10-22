package spotify_cs.observer;

import spotify_cs.composite.Album;
import spotify_cs.entities.Artist;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<ArtistObserver> observers = new ArrayList<>();
    public void addObserver(ArtistObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(ArtistObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(Artist artist, Album album) {
        for(ArtistObserver observer: observers) {
            observer.update(artist, album);
        }
    }

}

package spotify_cs.observer;

import spotify_cs.composite.Album;
import spotify_cs.entities.Artist;

public interface ArtistObserver {
    void update(Artist artist, Album newAlbum);
}

package spotify.entities;

import spotify.composite.Song;

import java.util.List;

public interface SpotifyOperations {
    List<Song> search(String query);
    void addNewSong(Song song);
}

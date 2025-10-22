package spotify.entities;

import spotify.SpotifyService;
import spotify.composite.Song;


public class Admin extends AbstractUser{
    private final SpotifyOperations spotifyService = SpotifyService.getInstance();
    public Admin(String name, String email) {
        super(name, email);
    }
    public void addNewSong(String title, String artist) {
        Song song = new Song.Builder()
                .withTitle(title)
                .withArtist(artist)
                .build();
        spotifyService.addNewSong(song);
    }
}

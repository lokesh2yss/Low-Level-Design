package spotify_cs.strategy;

import spotify_cs.composite.Song;
import spotify_cs.entities.Player;

public class FreePlaybackStrategy implements PlaybackStrategy{
    private int songsPlayed;
    private static final int SONGS_BEFORE_ADS = 3;

    public FreePlaybackStrategy(int initialSongsPlayed) {
        this.songsPlayed = initialSongsPlayed;
    }
    @Override
    public void play(Song song, Player player) {
        if (songsPlayed > 0 && songsPlayed % SONGS_BEFORE_ADS == 0) {
            System.out.println("\n>>> Playing Advertisement: 'Buy Spotify Premium for ad-free music!' <<<\n");
        }
        player.setCurrentSong(song);
        System.out.printf("Free User is now playing: %s%n", song);
        songsPlayed++;
    }
}

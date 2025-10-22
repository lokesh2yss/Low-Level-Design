package spotify_cs.strategy;

import spotify_cs.composite.Song;
import spotify_cs.entities.Player;
import spotify_cs.enums.SubscriptionTier;

public interface PlaybackStrategy {
    void play(Song song, Player player);
    static PlaybackStrategy getStrategy(SubscriptionTier tier, int songsPlayed) {
        return tier==SubscriptionTier.FREE? new FreePlaybackStrategy(songsPlayed): new PremiumPlaybackStrategy();
    }
}

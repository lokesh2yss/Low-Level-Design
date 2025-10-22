package spotify_cs.entities;

import spotify_cs.composite.Album;
import spotify_cs.enums.SubscriptionTier;
import spotify_cs.observer.ArtistObserver;
import spotify_cs.strategy.PlaybackStrategy;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User implements ArtistObserver {
    private final String id;
    private final String name;
    private final PlaybackStrategy playbackStrategy;
    private final Set<Artist> followedArtists = new HashSet<>();

    public User(String id, String name, PlaybackStrategy playbackStrategy) {
        this.id = id;
        this.name = name;
        this.playbackStrategy = playbackStrategy;
    }

    public void followArtist(Artist artist) {
        followedArtists.add(artist);
        artist.addObserver(this);
    }

    @Override
    public void update(Artist artist, Album newAlbum) {
        System.out.printf("[Notification for %s] Your followed artist %s just released a new album: %s!%n",
                this.name, artist.getName(), newAlbum.getTitle());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlaybackStrategy getPlaybackStrategy() {
        return playbackStrategy;
    }
    public static class Builder {
        private String id;
        private String name;
        private PlaybackStrategy playbackStrategy;
        public Builder(String name) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
        }
        public Builder withSubscription(SubscriptionTier tier, int songsPlayed) {
            this.playbackStrategy = PlaybackStrategy.getStrategy(tier, songsPlayed);
            return this;
        }
        public User build() {
            return new User(id, name, playbackStrategy);
        }
    }
}

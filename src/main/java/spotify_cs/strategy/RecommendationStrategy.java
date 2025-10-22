package spotify_cs.strategy;

import spotify_cs.composite.Song;

import java.util.List;

public interface RecommendationStrategy {
    List<Song> recommend(List<Song> allSongs);
}

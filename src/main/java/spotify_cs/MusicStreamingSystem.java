package spotify_cs;

import spotify_cs.entities.User;
import spotify_cs.composite.Song;
import spotify_cs.entities.Artist;
import spotify_cs.entities.Player;
import spotify_cs.service.RecommendationService;
import spotify_cs.service.SearchService;
import spotify_cs.strategy.GenreBasedRecommendationStrategy;

import java.util.*;

public class MusicStreamingSystem {
    private static volatile MusicStreamingSystem instance;

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Song> songs = new HashMap<>();
    private final Map<String, Artist> artists = new HashMap<>();

    private final Player player;
    private final RecommendationService recommendationService;
    private final SearchService searchService;
    public MusicStreamingSystem() {
        player = new Player();
        recommendationService = new RecommendationService(new GenreBasedRecommendationStrategy());
        searchService = new SearchService();
    }

    public static MusicStreamingSystem getInstance() {
        if(instance == null) {
            synchronized (MusicStreamingSystem.class) {
                if(instance == null) {
                    instance = new MusicStreamingSystem();
                }
            }
        }
        return instance;
    }
    public Song addSong(String id, String title, String  artistId, int duration) {
        Song song = new Song(id, title, artists.get(artistId), duration);
        songs.put(song.getId(), song);
        return song;
    }
    public void registerUser(User user) {
        users.put(user.getId(), user);
    }
    public void addArtist(Artist artist) {
        artists.put(artist.getId(), artist);
    }
    public List<Song> searchSongsByTitle(String title) {
        return searchService.searchSongsByTitle(new ArrayList<>(songs.values()), title);
    }

    public List<Song> getSongRecommendations() {
        return recommendationService.generateRecommendations(new ArrayList<>(songs.values()));
    }

    public Player getPlayer() { return player; }
}

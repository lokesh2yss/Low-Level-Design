package spotify.repositories;

import spotify.composite.Song;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SongRepository {
    private static final SongRepository instance = new SongRepository();
    private final Map<String, Song> songs = new ConcurrentHashMap<>();
    private SongRepository() {

    }
    public static SongRepository getInstance() {
        return instance;
    }
    public void save(Song song) {
        this.songs.put(song.getId(), song);
    }
    public Song findSongById(String id) {
        return this.songs.get(id);
    }

    public Map<String, Song> getSongs() {
        return songs;
    }
}

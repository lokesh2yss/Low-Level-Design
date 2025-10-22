package spotify.repositories;

import spotify.composite.Playlist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlaylistRepository {
    private static final PlaylistRepository instance = new PlaylistRepository();
    private final Map<String, Playlist> playlistMap = new ConcurrentHashMap<>();
    private PlaylistRepository() {}

    public static PlaylistRepository getInstance() {
        return instance;
    }
    public void save(Playlist playlist) {
        playlistMap.put(playlist.getId(), playlist);
    }
    public Playlist findPlaylistById(String id) {
        return playlistMap.get(id);
    }
}

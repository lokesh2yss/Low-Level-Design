package spotify;

import spotify.composite.Song;
import spotify.entities.SpotifyOperations;
import spotify.repositories.PlaylistRepository;
import spotify.repositories.SongRepository;
import spotify.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SpotifyService implements SpotifyOperations {
    private static final SpotifyService INSTANCE = new SpotifyService();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final SongRepository songRepository = SongRepository.getInstance();
    private final PlaylistRepository playlistRepository = PlaylistRepository.getInstance();

    private SpotifyService() {}

    public static SpotifyService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Song> search(String query) {
        return songRepository.getSongs().values()
                .stream()
                .filter(song -> song.getArtist().toLowerCase().contains(query.toLowerCase()) || song.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
    public void addNewSong(Song song) {
        songRepository.save(song);
    }
}

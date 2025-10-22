package spotify.entities;

import spotify.SpotifyService;
import spotify.composite.Playlist;
import spotify.composite.Song;
import spotify.repositories.PlaylistRepository;
import spotify.repositories.SongRepository;

import java.util.ArrayList;
import java.util.List;

public class User extends AbstractUser {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final SpotifyService spotifyService;
    private final List<Playlist> playlists = new ArrayList<>();

    public User(String name, String email, PlaylistRepository playlistRepository,
                SongRepository songRepository, SpotifyService spotifyService) {
        super(name, email);
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.spotifyService = spotifyService;

    }


    public Playlist createPlaylist(String name) {
        Playlist playlist = new Playlist(name, this);
        playlists.add(playlist);
        playlistRepository.save(playlist);
        return playlist;
    }

    public List<Song> searchSong(String query) {
        return spotifyService.search(query);
    }

    public void addSongToPlaylist(String playlistId, String songId) throws IllegalAccessException {
        Playlist playlist = playlistRepository.findPlaylistById(playlistId);
        if (!playlist.getOwner().getId().equals(this.getId())) {
            throw new IllegalAccessException("Cannot modify someone else's playlist");
        }
        Song song = songRepository.findSongById(songId);
        playlist.addSong(song);
    }


    public void play(Song song) {
        System.out.println("User :"+getName()+" is playing song: "+song.getTitle());
    }
}

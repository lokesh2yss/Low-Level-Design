package spotify;

import spotify.entities.Admin;
import spotify.composite.Playlist;
import spotify.composite.Song;
import spotify.entities.User;
import spotify.repositories.UserRepository;

import java.util.List;

public class SpotifyDemo {
    public static void main(String[] args) throws IllegalAccessException {
        SpotifyService spotifyService = SpotifyService.getInstance();
        UserRepository userRepository = UserRepository.getInstance();
        Admin admin = new Admin("Lokesh", "lokesh@gmail.com");
        admin.addNewSong("O mere din ke chain", "Kishor kumar");
        admin.addNewSong("Mere Samne wali khidki", "Kishor Kumar");
        admin.addNewSong("E mere varan ke logon", "Lata Mangeshkar");
        /*User user = new User("Virender", "vir@gmail.com");

        userRepository.save(user);
        List<Song> songs = user.searchSong("mere samne");
        user.play(songs.get(0));
        Playlist playlist = user.createPlaylist("My Playlist");
        user.addSongToPlaylist(playlist.getId(), songs.get(0).getId());
*/
    }
}

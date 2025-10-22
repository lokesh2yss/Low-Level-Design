package spotify_cs.service;

import spotify_cs.composite.Song;
import spotify_cs.entities.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    public List<Song> searchSongsByTitle(List<Song> songs, String query) {
        return songs.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Artist> searchArtistsByName(List<Artist> artists, String query) {
        return artists.stream()
                .filter(a -> a.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}

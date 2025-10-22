package spotify.composite;

import spotify.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Playlist implements PlayableEntity{
    private final String id;
    private final String title;
    private final User owner;
    private final List<Song> songs = new ArrayList<>();

    public Playlist(String title, User owner) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.owner = owner;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void play() {
        songs.forEach(Song::play);
    }

    public User getOwner() {
        return owner;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
    public void removeSong(Song song) {
        songs.remove(song);
    }
}

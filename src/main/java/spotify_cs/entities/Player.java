package spotify_cs.entities;

import spotify_cs.composite.Playable;
import spotify_cs.composite.Song;
import spotify_cs.enums.PlayerStatus;
import spotify_cs.state.PlayerState;
import spotify_cs.state.StoppedState;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private PlayerState playerState;
    private PlayerStatus playerStatus;
    private List<Song> queue = new ArrayList<>();
    private int currentIndex = -1;
    private User currentUser;
    private Song currentSong;
    public Player() {
        this.playerState = new StoppedState();
        this.playerStatus = PlayerStatus.STOPPED;
    }
    public void load(Playable playable, User user) {
        this.currentUser = user;
        this.queue = playable.getTracks();
        this.currentIndex = 0;
        System.out.printf("Loaded %d tracks for user %s.%n", queue.size(), user.getName());
        this.playerState = new StoppedState();
    }
    public void playCurrentSongInQueue() {
        if(currentIndex >=0 && currentIndex < queue.size()) {
            Song songToPlay = queue.get(currentIndex);
            currentUser.getPlaybackStrategy().play(songToPlay, this);
        }
    }
    public void clickPlay() {
        this.playerState.play(this);
    }
    public void clickPause() {
        this.playerState.pause(this);
    }
    public void clickStop() {
        this.playerState.stop(this);
    }
    public void clickNext() {
        if(currentIndex < queue.size()-1) {
            currentIndex++;
            playCurrentSongInQueue();
        }
        else {
            System.out.println("End of queue.");
            playerState.stop(this);
        }
    }
    public void changeState(PlayerState state) {
        this.playerState = state;
    }
    public void setStatus(PlayerStatus status) {
        this.playerStatus = status;
    }
    public void setCurrentSong(Song song) {
        this.currentSong = song;
    }
    public boolean hasQueue() {
        return !this.queue.isEmpty();
    }
}

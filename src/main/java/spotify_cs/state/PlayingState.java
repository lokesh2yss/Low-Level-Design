package spotify_cs.state;

import spotify_cs.entities.Player;
import spotify_cs.enums.PlayerStatus;

public class PlayingState implements PlayerState{
    @Override
    public void play(Player player) {
        System.out.println("Already playing");
    }

    @Override
    public void pause(Player player) {
        System.out.println("Changing player state from playing to paused");
        player.changeState(new PausedState());
        player.setStatus(PlayerStatus.PAUSED);
    }

    @Override
    public void stop(Player player) {
        System.out.println("Changing player state from playing to stop");
        player.changeState(new StoppedState());
        player.setStatus(PlayerStatus.STOPPED);
    }
}

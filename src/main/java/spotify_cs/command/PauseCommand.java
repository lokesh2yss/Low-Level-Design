package spotify_cs.command;

import spotify_cs.entities.Player;

public class PauseCommand implements Command{
    private final Player player;

    public PauseCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.clickPause();
    }
}

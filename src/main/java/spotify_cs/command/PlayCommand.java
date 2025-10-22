package spotify_cs.command;

import spotify_cs.entities.Player;

public class PlayCommand implements Command{
    private final Player player;

    public PlayCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.clickPlay();
    }
}

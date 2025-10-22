package spotify_cs.command;

import spotify_cs.entities.Player;

public class StopCommand implements Command {
    private final Player player;

    public StopCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.clickStop();
    }
}

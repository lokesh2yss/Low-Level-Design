package spotify_cs.command;

import spotify_cs.entities.Player;

public class NextTrackCommand implements Command{
    private final Player player;

    public NextTrackCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.clickNext();
    }
}

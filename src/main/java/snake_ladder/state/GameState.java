package snake_ladder.state;

import snake_ladder.entities.Game;
import snake_ladder.entities.Player;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);
}

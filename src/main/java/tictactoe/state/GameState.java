package tictactoe.state;

import tictactoe.entities.Game;
import tictactoe.entities.Player;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);
}

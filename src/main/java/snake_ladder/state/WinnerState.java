package snake_ladder.state;

import snake_ladder.entities.Game;
import snake_ladder.entities.Player;
import tictactoe.exceptions.InvalidMoveException;

public class WinnerState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game is already over. " + game.getWinner().getName() + " has won.");

    }
}

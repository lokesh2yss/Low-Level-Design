package tictactoe.state;

import tictactoe.entities.Game;
import tictactoe.entities.Player;
import tictactoe.exceptions.InvalidMoveException;

public class WinnerState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game is already over. " + game.getWinner().getName() + " has won.");
    }
}

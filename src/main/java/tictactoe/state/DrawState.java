package tictactoe.state;

import tictactoe.entities.Game;
import tictactoe.entities.Player;
import tictactoe.exceptions.InvalidMoveException;

public class DrawState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game is already over. It was a draw.");
    }
}

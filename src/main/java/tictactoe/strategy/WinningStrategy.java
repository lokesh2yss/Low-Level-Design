package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player);
}

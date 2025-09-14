package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        for(int row = 0; row < size; row++) {
            boolean rowWin = true;
            for(int col= 0; col<size; col++) {
                if(board.getCell(row, col).getSymbol() != player.getSymbol()) {
                    rowWin = false;
                    break;
                }
            }
            if(rowWin) return true;
        }
        return false;
    }
}

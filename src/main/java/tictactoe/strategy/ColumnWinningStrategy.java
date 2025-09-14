package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        for(int col = 0; col < size; col++) {
            boolean colWin = true;
            for(int row=0; row < size; row++) {
                if(board.getCell(row, col).getSymbol() != player.getSymbol()) {
                    colWin = false;
                    break;
                }
            }
            if(colWin) return true;
        }
        return false;
    }
}

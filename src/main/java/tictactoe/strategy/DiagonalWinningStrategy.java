package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        boolean mainDiagonalWin = true;
        for(int i=0; i<size; i++) {
            if(board.getCell(i, i).getSymbol() != player.getSymbol()) {
                mainDiagonalWin = false;
                break;
            }
        }
        if(mainDiagonalWin) return true;

        boolean negDiagonalWin = true;
        for(int i= 0; i < size; i++) {
            if(board.getCell(i, size - i-1).getSymbol() != player.getSymbol()) {
                negDiagonalWin = false;
                break;
            }
        }
        return negDiagonalWin;
    }
}

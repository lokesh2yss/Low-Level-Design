package minesweeper_game.strategy;

import minesweeper_game.entities.Board;

import java.util.Random;

public class RandomMinePlacementStrategy implements MinePlacementStrategy{
    @Override
    public void placeMines(Board board, int minesCount) {
        Random random = new Random();
        int rows = board.getRows();
        int cols = board.getCols();
        int minesPlaced = 0;
        while(minesPlaced < minesCount) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            if(!board.getCell(r, c).isMine()) {
                board.getCell(r, c).setMine(true);
                minesPlaced++;
            }
        }
    }
}

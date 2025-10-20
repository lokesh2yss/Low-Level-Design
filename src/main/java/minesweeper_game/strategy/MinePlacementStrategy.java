package minesweeper_game.strategy;

import minesweeper_game.entities.Board;

public interface MinePlacementStrategy {
    void placeMines(Board board, int minesCount);
}

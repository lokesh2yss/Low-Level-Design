package minesweeper_game.state;

import minesweeper_game.entities.Cell;

public interface CellState {
    void reveal(Cell context);
    void flag(Cell context);
    void unflag(Cell context);
    char getDisplayChar();
}

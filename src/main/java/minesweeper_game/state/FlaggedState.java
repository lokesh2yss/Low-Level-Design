package minesweeper_game.state;

import minesweeper_game.entities.Cell;

public class FlaggedState implements CellState {
    @Override
    public void reveal(Cell context) {
        System.out.println("Cannot reveal a flagged cell. unflag it first");
    }

    @Override
    public void flag(Cell context) {
        //unflag
        context.setState(new HiddenState());
    }

    @Override
    public void unflag(Cell context) {
        context.setState(new HiddenState());
    }

    @Override
    public char getDisplayChar() {
        return 'F';
    }
}

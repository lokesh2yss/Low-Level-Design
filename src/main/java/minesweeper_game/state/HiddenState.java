package minesweeper_game.state;


import minesweeper_game.entities.Cell;

public class HiddenState implements CellState {
    @Override
    public void reveal(Cell context) {
        context.setState(new RevealedState());
    }

    @Override
    public void flag(Cell context) {
        context.setState(new FlaggedState());
    }

    @Override
    public void unflag(Cell context) {
        System.out.println("First flag the cell");
    }

    @Override
    public char getDisplayChar() {
        return '-';
    }
}

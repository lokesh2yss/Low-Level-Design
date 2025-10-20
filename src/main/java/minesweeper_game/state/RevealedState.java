package minesweeper_game.state;

import minesweeper_game.entities.Cell;

public class RevealedState implements CellState{
    @Override
    public void reveal(Cell context) {
        System.out.println("Cannot reveal an already revealed state");
    }

    @Override
    public void flag(Cell context) {
        // do nothing
    }

    @Override
    public void unflag(Cell context) {
        //do nothing
    }

    @Override
    public char getDisplayChar() {
        // This is handled by Cell's getDisplayChar method, as it needs access to mine count.
        // This method shouldn't be called directly when the state is Revealed.
        return ' ';
    }
}

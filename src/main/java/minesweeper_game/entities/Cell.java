package minesweeper_game.entities;

import minesweeper_game.state.CellState;
import minesweeper_game.state.FlaggedState;
import minesweeper_game.state.HiddenState;
import minesweeper_game.state.RevealedState;

public class Cell {
    private CellState currentState;
    private int adjacentMinesCount;
    private boolean isMine;
    public Cell() {
        this.isMine = false;
        this.adjacentMinesCount = 0;
        this.currentState = new HiddenState();
    }

    public void setState(CellState state) {
        this.currentState = state;
    }
    public void reveal() {
        this.currentState.reveal(this);
    }
    public void flag() {
        this.currentState.flag(this);
    }
    public void unflag() {
        currentState.unflag(this);
    }
    public boolean isRevealed() {
        return this.currentState instanceof RevealedState;
    }
    public boolean isFlagged() {
        return currentState instanceof FlaggedState;
    }
    public boolean isMine() {
        return isMine;
    }
    public char getDisplayChar() {
        if(isRevealed()) {
            if(isMine) return '*';
            return adjacentMinesCount > 0 ? (char) (adjacentMinesCount+'0') : ' ';
        }
        else {
            return currentState.getDisplayChar();
        }
    }

    public void setAdjacentMinesCount(int minesCount) {
        this.adjacentMinesCount = minesCount;
    }
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }
    public int getAdjacentMinesCount() {
        return adjacentMinesCount;
    }
}

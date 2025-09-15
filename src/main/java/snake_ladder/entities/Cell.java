package snake_ladder.entities;

import snake_ladder.enums.Symbol;

public class Cell {
    private Symbol symbol;
    public Cell() {
        this.symbol = Symbol.EMPTY;
    }
    public Cell(Symbol symbol) {
        this.symbol = symbol;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}

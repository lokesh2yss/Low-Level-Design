package snake_ladder.enums;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY('_'),
    ST('T'),
    SH('H'),
    LT('t'),
    LH('h');

    private final char symbol;
    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public char getChar() {
        return symbol;
    }
}

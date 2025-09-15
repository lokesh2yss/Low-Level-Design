package snake_ladder.entities;

import snake_ladder.enums.Symbol;

public class Player {
    private final String name;
    private final Symbol symbol;
    private int x;
    private int y;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int[] getPosition() {
        return new int[] {x, y};
    }
    public String getName() {
        return name;
    }
    public Symbol getSymbol() {
        return symbol;
    }
}

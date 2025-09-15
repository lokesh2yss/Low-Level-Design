package snake_ladder.entities;

public class Snake {
    private final int sX, sY, eX, eY;

    public Snake(int sX, int sY, int eX, int eY) {
        this.sX = sX;
        this.sY = sY;
        this.eX = eX;
        this.eY = eY;
    }
    public int[] getStart() {
        return new int[] {sX, sY};
    }
    public int[] getEnd() {
        return new int[] {eX, eY};
    }
}

package snake_ladder.entities;

public class Ladder {
    private final int sX, sY, eX, eY;

    public Ladder(int sX, int sY, int eX, int eY) {
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

package snake_ladder_cs.entities;

public class Ladder extends BoardEntity{
    public Ladder(int start, int end) {
        super(start, end);
        if(start >= end) {
            throw new IllegalArgumentException("Ladder's base should have a lower value than its top");
        }
    }
}

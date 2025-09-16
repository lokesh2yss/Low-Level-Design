package snake_ladder_cs.entities;

public class Snake extends BoardEntity{
    public Snake(int start, int end) {
        super(start, end);
        if(start <= end) {
            throw new IllegalArgumentException("Snake's head must have value larger than its tail");
        }
    }
}

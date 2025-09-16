package snake_ladder_cs.entities;

public class Player {
    private final String name;
    private int position;

    public Player(String name) {
        this.name = name;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
    public String getName() {
        return name;
    }
}

package elevator_system.entities;

import elevator_system.enums.Direction;
import elevator_system.enums.RequestSource;

public class Request {
    private final int targetFloor;
    private final Direction direction;
    private final RequestSource source;

    public Request(int targetFloor, Direction direction, RequestSource source) {
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.source = source;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public RequestSource getSource() {
        return source;
    }

    @Override
    public String toString() {
        return source + " Request to floor " + targetFloor +
                (source == RequestSource.EXTERNAL ? " going " + direction : "");

    }
}

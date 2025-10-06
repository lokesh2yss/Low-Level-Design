package elevator_system.state;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;

public interface ElevatorState {
    void move(Elevator elevator);
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
}

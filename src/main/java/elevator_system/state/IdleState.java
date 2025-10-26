package elevator_system.state;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;

public class IdleState implements ElevatorState {

    @Override
    public void move(Elevator elevator) {
        if (!elevator.getUpRequests().isEmpty()) {
            elevator.setState(new MovingUpState());
        } else if (!elevator.getDownRequests().isEmpty()) {
            elevator.setState(new MovingDownState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int target = request.getTargetFloor();
        int current = elevator.getCurrentFloor();

        if (target > current) {
            elevator.getUpRequests().add(target);
        } else if (target < current) {
            elevator.getDownRequests().add(target);
        } else {
            System.out.println("Elevator " + elevator.getId() + " already at floor " + current);
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }
}

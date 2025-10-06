package elevator_system.state;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;

public class IdleState implements ElevatorState{
    @Override
    public void move(Elevator elevator) {
        if(!elevator.getUpRequests().isEmpty()) {
            elevator.setState(new MovingUpState());
        }
        else if(!elevator.getDownRequests().isEmpty()) {
            elevator.setState(new MovingDownState());
        }
        // else remain idle
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        if(request.getTargetFloor() > elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getTargetFloor());
        }
        else if(request.getTargetFloor() < elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getTargetFloor());
        }
        // If request is for current floor, doors would open (handled implicitly by moving to that floor)
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }
}

package elevator_system.state;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;
import elevator_system.enums.RequestSource;

public class MovingUpState implements ElevatorState {

    @Override
    public void move(Elevator elevator) {
        if (elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = elevator.getUpRequests().iterator().next();

        // Move one floor up
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

        if (elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            elevator.getUpRequests().remove(nextFloor);
        }

        // Change direction only after finishing all upRequests
        if (elevator.getUpRequests().isEmpty() && !elevator.getDownRequests().isEmpty()) {
            elevator.setState(new MovingDownState());
        } else if (elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int target = request.getTargetFloor();
        int current = elevator.getCurrentFloor();

        if (request.getSource() == RequestSource.INTERNAL) {
            if (target > current) elevator.getUpRequests().add(target);
            else elevator.getDownRequests().add(target);
            return;
        }

        if (request.getDirection() == Direction.UP && target >= current)
            elevator.getUpRequests().add(target);
        else
            elevator.getDownRequests().add(target);
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }
}

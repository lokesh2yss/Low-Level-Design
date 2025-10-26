package elevator_system.state;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;
import elevator_system.enums.RequestSource;

public class MovingDownState implements ElevatorState {

    @Override
    public void move(Elevator elevator) {
        if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = elevator.getDownRequests().iterator().next();
        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);

        if (elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            elevator.getDownRequests().remove(nextFloor);
        }

        if (elevator.getDownRequests().isEmpty() && !elevator.getUpRequests().isEmpty()) {
            elevator.setState(new MovingUpState());
        } else if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int target = request.getTargetFloor();
        int current = elevator.getCurrentFloor();

        if (request.getSource() == RequestSource.INTERNAL) {
            if (target < current) elevator.getDownRequests().add(target);
            else elevator.getUpRequests().add(target);
            return;
        }

        if (request.getDirection() == Direction.DOWN && target <= current)
            elevator.getDownRequests().add(target);
        else
            elevator.getUpRequests().add(target);
    }

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }
}

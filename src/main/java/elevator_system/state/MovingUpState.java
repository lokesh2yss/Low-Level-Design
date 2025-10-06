package elevator_system.state;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;
import elevator_system.enums.RequestSource;

public class MovingUpState implements ElevatorState{
    @Override
    public void move(Elevator elevator) {
        if(elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }
        Integer nextFloor = elevator.getUpRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor()+1);
        if(elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            elevator.getUpRequests().pollFirst();
        }
        if (elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        // Internal requests always get added to the appropriate queue
        if(request.getSource() == RequestSource.INTERNAL) {
            if(request.getTargetFloor() > elevator.getCurrentFloor()) {
                elevator.getUpRequests().add(request.getTargetFloor());
            }
            else {
                elevator.getDownRequests().add(request.getTargetFloor());
            }
            return;
        }
        // External requests
        if(request.getDirection() == Direction.UP && request.getTargetFloor() >= elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getTargetFloor());
        }
        else if(request.getDirection() == Direction.DOWN) {
            elevator.getDownRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }
}

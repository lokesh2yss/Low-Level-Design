package elevator_system.strategy;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;
import elevator_system.enums.Direction;

import java.util.List;
import java.util.Optional;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Optional<Elevator> selectElevator(List<Elevator> elevators, Request request) {
        int targetFloor = request.getTargetFloor();
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator: elevators) {
            if(isSuitable(elevator, request)) {
                int distance = Math.abs(elevator.getCurrentFloor() - targetFloor);
                if (distance < minDistance) {
                    bestElevator = elevator;
                    minDistance = distance;
                }
            }
        }
        return Optional.ofNullable(bestElevator);
    }
    private boolean isSuitable(Elevator elevator, Request request) {
        if(elevator.getDirection() == Direction.IDLE) {
            return true;
        }
        if(elevator.getDirection() == request.getDirection()) {
            if(request.getDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getTargetFloor()) {
                return true;
            }
            if(request.getDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getTargetFloor()) {
                return true;
            }
        }
        return false;
    }
}

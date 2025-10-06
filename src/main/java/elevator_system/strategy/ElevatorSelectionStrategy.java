package elevator_system.strategy;

import elevator_system.entities.Elevator;
import elevator_system.entities.Request;

import java.util.List;
import java.util.Optional;

public interface ElevatorSelectionStrategy {
    Optional<Elevator> selectElevator(List<Elevator> elevators, Request request);
}

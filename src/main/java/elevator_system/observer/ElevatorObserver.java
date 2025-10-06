package elevator_system.observer;

import elevator_system.entities.Elevator;

public interface ElevatorObserver {
    void update(Elevator elevator);
}

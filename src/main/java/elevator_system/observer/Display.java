package elevator_system.observer;

import elevator_system.entities.Elevator;

public class Display implements ElevatorObserver{
    @Override
    public void update(Elevator elevator) {
        System.out.println("[DISPLAY] Elevator " + elevator.getId() +
                " | Current Floor: " + elevator.getCurrentFloor() +
                " | Direction: " + elevator.getDirection());
    }
}

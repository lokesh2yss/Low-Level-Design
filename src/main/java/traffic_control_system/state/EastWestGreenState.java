package traffic_control_system.state;

import traffic_control_system.entities.IntersectionController;
import traffic_control_system.enums.Direction;
import traffic_control_system.enums.LightColor;

public class EastWestGreenState implements IntersectionState{
    @Override
    public void handle(IntersectionController context) throws InterruptedException {
        System.out.printf("\n--- INTERSECTION %d: Cycle -> East-West GREEN ---\n", context.getId());
        // Turn East and West green, ensure North and South are red
        context.getLight(Direction.EAST).startGreen();
        context.getLight(Direction.WEST).startGreen();
        context.getLight(Direction.NORTH).setColor(LightColor.RED);
        context.getLight(Direction.SOUTH).setColor(LightColor.RED);

        // Wait for green light duration
        Thread.sleep(context.getGreenDuration());

        // Transition East and West to Yellow
        context.getLight(Direction.EAST).transition();
        context.getLight(Direction.WEST).transition();

        // Wait for yellow light duration
        Thread.sleep(context.getYellowDuration());

        // Transition East and West to Red
        context.getLight(Direction.EAST).transition();
        context.getLight(Direction.WEST).transition();

        // Change the intersection's state back to let North-South go
        context.setState(new NorthSouthGreenState());



    }
}

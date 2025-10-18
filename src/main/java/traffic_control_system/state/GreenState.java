package traffic_control_system.state;

import traffic_control_system.entities.TrafficLight;
import traffic_control_system.enums.LightColor;

public class GreenState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.GREEN);
        // After being green, the next state is yellow.
        context.setNextState(new YellowState());
    }
}

package traffic_control_system.state;

import traffic_control_system.entities.TrafficLight;
import traffic_control_system.enums.LightColor;

public class YellowState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.YELLOW);
        // After being yellow, the next state is red.
        context.setNextState(new RedState());
    }
}

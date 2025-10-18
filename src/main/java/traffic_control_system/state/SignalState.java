package traffic_control_system.state;

import traffic_control_system.entities.TrafficLight;

public interface SignalState {
    void handle(TrafficLight context);
}

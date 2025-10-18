package traffic_control_system.observer;

import traffic_control_system.enums.Direction;
import traffic_control_system.enums.LightColor;

public interface TrafficObserver {
    void update(int intersectionId, Direction direction, LightColor color);
}

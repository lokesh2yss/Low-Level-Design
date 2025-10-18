package traffic_control_system.state;

import traffic_control_system.entities.IntersectionController;

public interface IntersectionState {
    void handle(IntersectionController context) throws InterruptedException;
}

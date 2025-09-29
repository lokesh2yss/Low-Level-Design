package taskManagementSystem.observer;

import taskManagementSystem.entities.Task;

public interface TaskObserver {
    void update(Task task, String changeType);
}

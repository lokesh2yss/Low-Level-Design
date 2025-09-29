package taskManagementSystem.state;

import taskManagementSystem.entities.Task;
import taskManagementSystem.enums.TaskStatus;

public interface TaskState {
    void startProgress(Task task);
    void completeTask(Task task);
    void reopenTask(Task task);
    TaskStatus getStatus();
}

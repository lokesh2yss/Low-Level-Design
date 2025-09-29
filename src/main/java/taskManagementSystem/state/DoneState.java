package taskManagementSystem.state;

import taskManagementSystem.entities.Task;
import taskManagementSystem.enums.TaskStatus;

public class DoneState implements TaskState{
    @Override
    public void startProgress(Task task) {
        System.out.println("Task is already done");
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("Task is already done");
    }

    @Override
    public void reopenTask(Task task) {
        task.setState(new TodoState());
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.DONE;
    }
}

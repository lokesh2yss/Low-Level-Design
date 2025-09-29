package taskManagementSystem.observer;

import taskManagementSystem.entities.Task;

public class ActivityLogger implements TaskObserver{
    @Override
    public void update(Task task, String changeType) {
        System.out.println("LOGGER: Task '" + task.getTitle() + "' was updated. Change: " + changeType);
    }
}

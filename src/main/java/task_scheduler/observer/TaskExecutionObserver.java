package task_scheduler.observer;

import task_scheduler.entities.ScheduledTask;

public interface TaskExecutionObserver {
    void onTaskStarted(ScheduledTask task);
    void onTaskCompleted(ScheduledTask task);
    void onTaskFailed(ScheduledTask task, Exception e);
}

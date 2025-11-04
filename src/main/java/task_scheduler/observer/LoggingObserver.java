package task_scheduler.observer;

import task_scheduler.entities.ScheduledTask;

import java.time.LocalTime;

public class LoggingObserver implements TaskExecutionObserver{
    @Override
    public void onTaskStarted(ScheduledTask task) {
        System.out.printf("[LOG - %s] [%s] Task %s started.%n", LocalTime.now(), Thread.currentThread().getName(), task.getId());
    }

    @Override
    public void onTaskCompleted(ScheduledTask task) {
        System.out.printf("[LOG - %s] [%s] Task %s completed successfully.%n", LocalTime.now(), Thread.currentThread().getName(), task.getId());
    }

    @Override
    public void onTaskFailed(ScheduledTask task, Exception e) {
        System.err.printf("[LOG - %s] [%s] Task %s failed: %s%n", LocalTime.now(), Thread.currentThread().getName(), task.getId(), e.getMessage());
    }
}

package task_scheduler.entities;

import task_scheduler.strategy.SchedulingStrategy;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ScheduledTask implements Comparable<ScheduledTask> {
    private final String id;
    private final Task task;
    private final SchedulingStrategy schedulingStrategy;
    private LocalDateTime lastExecutionTime;
    private LocalDateTime nextExecutionTime;

    public ScheduledTask(Task task, SchedulingStrategy schedulingStrategy) {
        this.id = UUID.randomUUID().toString();
        this.task = task;
        this.schedulingStrategy = schedulingStrategy;
        updateNextExecutionTime();
    }
    public void updateNextExecutionTime() {
        Optional<LocalDateTime> nextTime = schedulingStrategy.getNextExecutionTime(this.lastExecutionTime);
        this.nextExecutionTime = nextTime.orElse(null);
    }
    public void updateLastExecutionTime() {
        this.lastExecutionTime = nextExecutionTime;
    }
    @Override
    public int compareTo(ScheduledTask o) {
        return this.nextExecutionTime.compareTo(o.nextExecutionTime);
    }
    public boolean hasMoreExecutions() {
        return nextExecutionTime != null;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getNextExecutionTime() {
        return nextExecutionTime;
    }

    public LocalDateTime getLastExecutionTime() {
        return lastExecutionTime;
    }

    public Task getTask() {
        return task;
    }
}

package task_scheduler.strategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class RecurringSchedulingStrategy implements SchedulingStrategy {
    private final Duration interval;

    public RecurringSchedulingStrategy(Duration interval) {
        this.interval = interval;
    }

    @Override
    public Optional<LocalDateTime> getNextExecutionTime(LocalDateTime lastExecutionTime) {
        // If first run, schedule from now. Otherwise, schedule from the last execution time.
        LocalDateTime baseTime = (lastExecutionTime == null) ? LocalDateTime.now() : lastExecutionTime;
        return Optional.of(baseTime.plus(interval));
    }
}

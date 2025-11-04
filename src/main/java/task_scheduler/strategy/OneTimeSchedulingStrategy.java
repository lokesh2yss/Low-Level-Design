package task_scheduler.strategy;

import java.time.LocalDateTime;
import java.util.Optional;

public class OneTimeSchedulingStrategy implements SchedulingStrategy {
    private final LocalDateTime executionTime;

    public OneTimeSchedulingStrategy(LocalDateTime executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public Optional<LocalDateTime> getNextExecutionTime(LocalDateTime lastExecutionTime) {
        return lastExecutionTime==null? Optional.of(executionTime): Optional.empty();
    }
}

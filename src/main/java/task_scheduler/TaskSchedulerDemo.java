package task_scheduler;

import task_scheduler.entities.DataBackupTask;
import task_scheduler.entities.PrintMessageTask;
import task_scheduler.entities.Task;
import task_scheduler.observer.LoggingObserver;
import task_scheduler.strategy.OneTimeSchedulingStrategy;
import task_scheduler.strategy.RecurringSchedulingStrategy;
import task_scheduler.strategy.SchedulingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskSchedulerDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. Setup the facade and observers
        TaskSchedulerService scheduler = TaskSchedulerService.getInstance();
        scheduler.addObserver(new LoggingObserver());

        // 2. Initialize the scheduler
        scheduler.initialize(10);

        // 3. Define tasks and strategies
        // Scenario 1: One-time task, 5 seconds from now
        Task oneTimeTask = new PrintMessageTask("This is a one-time task.");
        SchedulingStrategy oneTimeStrategy = new OneTimeSchedulingStrategy(LocalDateTime.now().plusSeconds(1));

        // Scenario 2: Recurring task, every 3 seconds
        Task recurringTask = new PrintMessageTask("This is a recurring task.");
        SchedulingStrategy recurringStrategy = new RecurringSchedulingStrategy(Duration.ofSeconds(2));

        // Scenario 3: A long-running backup task, scheduled to run in 1 second
        Task backupTask = new DataBackupTask("/data/source", "/data/backup");
        SchedulingStrategy longRunningRecurringStrategy = new OneTimeSchedulingStrategy(LocalDateTime.now().plusSeconds(3));

        // 4. Schedule the tasks using the facade
        System.out.println("Scheduling tasks...");
        scheduler.schedule(oneTimeTask, oneTimeStrategy);
        scheduler.schedule(recurringTask, recurringStrategy);
        scheduler.schedule(backupTask, longRunningRecurringStrategy);

        // 5. Let the demo run for a while
        System.out.println("Scheduler is running. Waiting for tasks to execute... (Demo will run for 10 seconds)");
        Thread.sleep(6000);

        // 6. Shutdown the scheduler
        scheduler.shutdown();

    }
}

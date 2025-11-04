package task_scheduler;

import task_scheduler.entities.ScheduledTask;
import task_scheduler.entities.Task;
import task_scheduler.observer.TaskExecutionObserver;
import task_scheduler.strategy.SchedulingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class TaskSchedulerService {
    private static final TaskSchedulerService instance = new TaskSchedulerService();
    private final PriorityBlockingQueue<ScheduledTask> taskQueue = new PriorityBlockingQueue<>();
    private final List<TaskExecutionObserver> observers = new ArrayList<>();
    private Thread[] workers;
    private volatile boolean running = true;
    private TaskSchedulerService() {}
    public static TaskSchedulerService getInstance() {
        return instance;
    }
    public void initialize(int workerCount) {
        if(workerCount <= 0) {
            throw new IllegalArgumentException("Worker count must be >= 1");
        }
        workers = new Thread[workerCount];
        startWorkers();
    }
    public String schedule(Task task, SchedulingStrategy schedulingStrategy) {
        ScheduledTask scheduledTask = new ScheduledTask(task, schedulingStrategy);
        taskQueue.put(scheduledTask);
        return scheduledTask.getId();
    }
    private void startWorkers() {
        for(int i=0; i< workers.length; i++) {
            workers[i] = new Thread(this::runWorker, "WorkerThread-"+i);
            workers[i].setDaemon(true);
            workers[i].start();
        }
    }
    private void runWorker() {
        while (running) {
            try {
                // take() blocks until an element is available.
                ScheduledTask task = taskQueue.take();
                LocalDateTime now = LocalDateTime.now();
                long waitTime = 0;
                if(task.getNextExecutionTime().isAfter(now)) {
                    waitTime = Duration.between(now, task.getNextExecutionTime()).toMillis();
                }
                if(waitTime > 0) {
                    // Wait for the scheduled time.
                    Thread.sleep(waitTime);
                }
                // Check if a higher-priority task has arrived while we were sleeping
                ScheduledTask head  = taskQueue.peek();
                if(head != null && head.compareTo(task) < 0) {
                    taskQueue.put(task); // Put our task back and let the higher-priority one run
                    continue;
                }
                // --- Execute the task ---
                execute(task);
            } catch (InterruptedException e) {
                // This is the expected way to stop the worker thread.
                Thread.currentThread().interrupt();
                break; // Exit the loop
            }
        }
        System.out.printf("%s stopped.%n", Thread.currentThread().getName());
    }
    private void execute(ScheduledTask task) {
        observers.forEach(o-> o.onTaskStarted(task));
        try {
            task.getTask().execute();
            task.updateLastExecutionTime();
            observers.forEach(o -> o.onTaskCompleted(task));
        }catch (Exception e) {
            observers.forEach(o-> o.onTaskFailed(task, e));
            System.err.printf("Task %s failed with error: %s%n", task.getId(), e.getMessage());
        }
        finally {
            // --- Re-scheduling logic ---
            // Must be done whether the task succeeded or failed.
            task.updateNextExecutionTime();
            if(task.hasMoreExecutions()) {
                taskQueue.put(task);
            }
            else {
                System.out.printf("Task %s has no more executions and will not be rescheduled.%n", task.getId());
            }
        }
    }
    public void shutdown() {
        running = false;
        for(Thread worker: workers) {
            worker.interrupt();
        }
        System.out.println("Scheduler shut down.");
    }
    public void addObserver(TaskExecutionObserver observer) {
        observers.add(observer);
    }
}

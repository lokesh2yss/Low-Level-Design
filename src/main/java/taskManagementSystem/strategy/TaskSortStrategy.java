package taskManagementSystem.strategy;

import taskManagementSystem.entities.Task;

import java.util.List;

public interface TaskSortStrategy {
    void sort(List<Task> tasks);
}

package taskManagementSystem.strategy;

import taskManagementSystem.entities.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByPriority implements TaskSortStrategy{
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
    }
}

package taskManagementSystem.entities;

import taskManagementSystem.enums.TaskPriority;
import taskManagementSystem.enums.TaskStatus;
import taskManagementSystem.observer.ActivityLogger;
import taskManagementSystem.strategy.TaskSortStrategy;

import java.net.CookieManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskManagementSystem {
    private static TaskManagementSystem instance;
    private final Map<String, User> users;
    private final Map<String, Task> tasks;
    private final Map<String, TaskList> taskLists;

    public TaskManagementSystem() {
        users = new ConcurrentHashMap<>();
        tasks = new ConcurrentHashMap<>();
        taskLists = new ConcurrentHashMap<>();
    }
    public static synchronized TaskManagementSystem getInstance() {
        if(instance == null) {
            instance = new TaskManagementSystem();
        }
        return instance;
    }

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }
    public TaskList createTaskList(String name) {
        TaskList taskList = new TaskList(name);
        taskLists.put(taskList.getId(), taskList);
        return taskList;
    }

    public Task createTask(String title, String desc, LocalDate dueDate, TaskPriority priority, String createdByUserId) {
        User createdBy = users.get(createdByUserId);
        if(createdBy == null) {
            throw new IllegalArgumentException("User not found");
        }
        Task task = new Task.TaskBuilder(title)
                .description(desc)
                .dueDate(dueDate)
                .priority(priority)
                .createdBy(createdBy)
                .build();

        task.addObserver(new ActivityLogger());
        tasks.put(task.getId(), task);
        return task;
    }
    public List<Task> listTasksByUser(String userId) {
        User user = users.get(userId);
        return tasks.values().stream()
                .filter(task -> user.equals(task.getAssignee()))
                .collect(Collectors.toList());
    }
    public List<Task> listTaskByStatus(TaskStatus status) {
        return tasks.values().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }
    public List<Task> searchTasks(String keyword, TaskSortStrategy sortStrategy) {
        List<Task> matchingTasks = new ArrayList<>();
        for(Task task: tasks.values()) {
            if(task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        sortStrategy.sort(matchingTasks);
        return matchingTasks;
    }
}

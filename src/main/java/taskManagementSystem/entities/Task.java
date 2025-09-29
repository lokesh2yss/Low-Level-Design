package taskManagementSystem.entities;

import taskManagementSystem.enums.TaskPriority;
import taskManagementSystem.enums.TaskStatus;
import taskManagementSystem.observer.TaskObserver;
import taskManagementSystem.state.TaskState;
import taskManagementSystem.state.TodoState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Task {
    private final String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private final User createdBy;
    private User assignee;
    private TaskState currentState;
    private final Set<Tag> tags;
    private final List<Comment> comments;
    private final List<Task> subtasks;
    private final List<ActivityLog> activityLogs;
    private final List<TaskObserver> observers;

    public Task(TaskBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.priority = builder.priority;
        this.createdBy = builder.createdBy;
        this.assignee = builder.assignee;
        this.currentState = new TodoState();
        this.tags = builder.tags;
        this.comments = new ArrayList<>();
        this.subtasks = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
        this.observers = new ArrayList<>();
        addLog("Task created with title: " + title);
    }
    public synchronized void setAssignee(User user) {
        this.assignee = user;
        addLog("Assigned to "+user.getName());
        notifyObservers("assignee");
    }
    public synchronized void updatePriority(TaskPriority priority) {
        this.priority = priority;
        notifyObservers("priority");
    }
    public synchronized void addComment(Comment comment) {
        comments.add(comment);
        addLog("Comment added by "+ comment.getAuthor().getName());
        notifyObservers("comment");
    }
    public synchronized void addSubtask(Task subtask) {
        subtasks.add(subtask);
        addLog("Subtask added: "+subtask.getTitle());
        notifyObservers("subtask_added");
    }

    // --- State Pattern Methods ---
    public void setState(TaskState state) {
        this.currentState  = state;
        addLog("Status changed to: "+ state.getStatus());
        notifyObservers("status");
    }
    public void startProgress() {
        currentState.startProgress(this);
    }
    public void completeTask() {
        currentState.completeTask(this);
    }
    public void reopenTask() {
        currentState.reopenTask(this);
    }
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(String changeType) {
        for(TaskObserver observer: observers) {
            observer.update(this, changeType);
        }
    }
    public void addLog(String logDescription) {
        this.activityLogs.add(new ActivityLog(logDescription));
    }
    public boolean isComposite() {
        return !subtasks.isEmpty();
    }

    public void display(String indent) {
        System.out.println(indent+"- "+title+" ["+ getStatus()+ "]");
        if(isComposite()) {
            for(Task subtask: subtasks) {
                subtask.display(indent+ " ");
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public User getAssignee() {
        return assignee;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }
    public TaskStatus getStatus() {
        return currentState.getStatus();
    }

    public static class TaskBuilder {
        private final String id;
        private String title;
        private String description = "";
        private LocalDate dueDate;
        private TaskPriority priority;
        private User createdBy;
        private User assignee;
        private Set<Tag> tags;

        public TaskBuilder(String title) {
            this.id = UUID.randomUUID().toString();
            this.title = title;
        }
        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }
        public TaskBuilder dueDate(LocalDate date) {
            this.dueDate = date;
            return this;
        }
        public TaskBuilder priority(TaskPriority priority) {
            this.priority = priority;
            return this;
        }
        public TaskBuilder assignee(User user) {
            this.assignee = user;
            return this;
        }
        public TaskBuilder createdBy(User user) {
            this.createdBy = user;
            return this;
        }
        public TaskBuilder tags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }
        public Task build() {
            return new Task(this);
        }
    }
}

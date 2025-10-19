package learning_platform.entities;

import learning_platform.composite.Course;

import java.util.HashMap;
import java.util.Map;

public class Enrollment {
    public enum Status {IN_PROGRESS, COMPLETED};
    private final String id;
    private final Student student;
    private final Course course;
    private final Map<String, Boolean> progress = new HashMap<>();
    private Status status;

    public Enrollment(String id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.status = Status.IN_PROGRESS;
    }
    public void markComponentComplete(String componentId) {
        progress.put(componentId, true);
    }
    public boolean isCourseCompleted() {
        return progress.size() == course.getContent().size();
    }
    public double getProgressPercentage() {
        int completedComponents = progress.size();
        return (double) completedComponents/course.getContent().size()*100;
    }

    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

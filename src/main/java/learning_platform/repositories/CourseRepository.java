package learning_platform.repositories;

import learning_platform.composite.Course;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CourseRepository {
    private static final CourseRepository INSTANCE = new CourseRepository();
    private final Map<String, Course> courses = new ConcurrentHashMap<>();

    public static CourseRepository getInstance() { return INSTANCE; }

    public void save(Course course) { courses.put(course.getId(), course); }

    public Course findById(String id) { return courses.get(id); }
}

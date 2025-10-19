package learning_platform.repositories;

import learning_platform.entities.Enrollment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnrollmentRepository {
    private static final EnrollmentRepository INSTANCE = new EnrollmentRepository();
    private final Map<String, Enrollment> enrollments = new ConcurrentHashMap<>();

    public static EnrollmentRepository getInstance() { return INSTANCE; }

    public void save(Enrollment enrollment) {
        enrollments.put(enrollment.getId(), enrollment);
    }

    public Enrollment findById(String id) {
        return enrollments.get(id);
    }
}

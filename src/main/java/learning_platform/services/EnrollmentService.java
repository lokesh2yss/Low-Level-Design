package learning_platform.services;

import learning_platform.composite.Course;
import learning_platform.entities.Enrollment;
import learning_platform.entities.Student;
import learning_platform.observer.ProgressObserver;
import learning_platform.repositories.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository = EnrollmentRepository.getInstance();
    private final List<ProgressObserver> observers = new ArrayList<>();

    public Enrollment enrollStudent(Student student, Course course) {
        String enrollmentId = getEnrollmentId(student.getId(), course.getId());
        Enrollment enrollment = new Enrollment(enrollmentId, student, course);
        enrollmentRepository.save(enrollment);
        return enrollment;

    }
    private String getEnrollmentId(String studentId, String courseId) {
        return studentId+"|"+courseId;
    }
    public void markComponentAsComplete(String studentId, String courseId, String componentId) {
        Enrollment enrollment = enrollmentRepository.findById(getEnrollmentId(studentId, courseId));
        enrollment.markComponentComplete(componentId);
        System.out.println("Progress for " + enrollment.getStudent().getName() + " in '" + enrollment.getCourse().getTitle() + "': "
                + String.format("%.2f", enrollment.getProgressPercentage()) + "%");
        if(enrollment.isCourseCompleted()) {
            enrollment.setStatus(Enrollment.Status.COMPLETED);
            notifyCourseCompletion(enrollment);
        }
        enrollmentRepository.save(enrollment);

    }
    public void addObserver(ProgressObserver observer) {
        observers.add(observer);
    }
    public void notifyCourseCompletion(Enrollment enrollment) {
        for(ProgressObserver observer: observers) {
            observer.onCourseCompleted(enrollment);
        }
    }
}

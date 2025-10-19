package learning_platform.facade;

import learning_platform.composite.Course;
import learning_platform.composite.CourseComponent;
import learning_platform.composite.Lecture;
import learning_platform.entities.Enrollment;
import learning_platform.entities.Instructor;
import learning_platform.entities.Student;
import learning_platform.factory.ContentFactory;
import learning_platform.observer.ProgressObserver;
import learning_platform.repositories.CourseRepository;
import learning_platform.repositories.UserRepository;
import learning_platform.services.EnrollmentService;

public class LearningPlatformFacade {
    private final UserRepository userRepository = UserRepository.getInstance();
    private final CourseRepository courseRepository = CourseRepository.getInstance();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    public void addProgressObserver(ProgressObserver observer) {
        enrollmentService.addObserver(observer);
    }
    public Student createStudent(String name, String email) {
        Student student = new Student(name, email);
        userRepository.save(student);
        return student;
    }
    public Instructor createInstructor(String name, String email) {
        Instructor instructor = new Instructor(name, email);
        userRepository.save(instructor);
        return instructor;
    }
    public Course createCourse(String courseId, String title, Instructor instructor) {
        Course course = new Course(courseId, title, instructor);
        courseRepository.save(course);
        return course;
    }
    public void addLectureToCourse(String courseId, String title, int duration) {
        Course course = courseRepository.findById(courseId);
        CourseComponent lecture  = ContentFactory.createLecture(title, duration);
        course.addContent(lecture);
    }
    public void addQuizToCourse(String courseId, String title, int questionCount) {
        Course course = courseRepository.findById(courseId);
        CourseComponent quiz = ContentFactory.createQuiz(title, questionCount);
        course.addContent(quiz);
    }
    public Enrollment enrollStudent(String studentId, String courseId) {
        Student student = (Student) userRepository.findById(studentId);
        Course course = courseRepository.findById(courseId);
        return enrollmentService.enrollStudent(student, course);
    }
    public void completeComponent(String studentId, String courseId, String componentId) {
        enrollmentService.markComponentAsComplete(studentId, courseId, componentId);
    }
}

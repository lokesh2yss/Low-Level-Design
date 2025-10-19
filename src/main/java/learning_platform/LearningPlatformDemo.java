package learning_platform;

import learning_platform.composite.Course;
import learning_platform.entities.Enrollment;
import learning_platform.entities.Instructor;
import learning_platform.entities.Student;
import learning_platform.facade.LearningPlatformFacade;
import learning_platform.observer.CertificateIssuer;
import learning_platform.observer.InstructorNotifier;
public class LearningPlatformDemo {
    public static void main(String[] args) {
        // 1. Setup the system facade and observers
        LearningPlatformFacade platform = new LearningPlatformFacade();
        platform.addProgressObserver(new CertificateIssuer());
        platform.addProgressObserver(new InstructorNotifier());

        // 2. Create users and a course
        Instructor instructor = platform.createInstructor("Dr. Smith", "smith@algomaster.io");
        Student alice = platform.createStudent("Alice", "smith@algomaster.io");
        Course javaCourse = platform.createCourse("JAVA-101", "Advanced Java", instructor);

        // 3. Add content to the course using the factory
        platform.addLectureToCourse(javaCourse.getId(), "Introduction to Design Patterns", 60);
        platform.addQuizToCourse(javaCourse.getId(), "SOLID Principles Quiz", 10);
        platform.addLectureToCourse(javaCourse.getId(), "Advanced Concurrency", 90);

        System.out.println("----------- Course Structure -----------");
        javaCourse.display();

        System.out.println("\n----------- Alice Enrolls and Makes Progress -----------");
        Enrollment alicesEnrollment = platform.enrollStudent(alice.getId(), javaCourse.getId());
        if (alicesEnrollment == null) { System.out.println("Enrollment failed."); return; }

        System.out.println(alice.getName() + " enrolled in '" + javaCourse.getTitle() + "'.");

        // Alice completes the first lecture
        String firstLectureId = javaCourse.getContent().get(0).getId();
        platform.completeComponent(alice.getId(), javaCourse.getId(), firstLectureId);

        // Alice completes the quiz
        String quizId = javaCourse.getContent().get(1).getId();
        platform.completeComponent(alice.getId(), javaCourse.getId(), quizId);

        System.out.println("\n----------- Alice Completes the Course (Triggers Observers) -----------");
        // Alice completes the final lecture
        String secondLectureId = javaCourse.getContent().get(2).getId();
        platform.completeComponent(alice.getId(), javaCourse.getId(), secondLectureId);

    }
}

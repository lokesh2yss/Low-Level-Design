package learning_platform.observer;

import learning_platform.entities.Enrollment;

public class InstructorNotifier implements ProgressObserver{
    @Override
    public void onCourseCompleted(Enrollment enrollment) {
        System.out.println("--- OBSERVER (InstructorNotifier) ---");
        System.out.println("Notifying instructor " + enrollment.getCourse().getInstructor().getName() +
                " that " + enrollment.getStudent().getName() + " has completed the course '" +
                enrollment.getCourse().getTitle() + "'.");
        System.out.println("-------------------------------------");
    }
}

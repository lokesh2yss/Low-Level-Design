package learning_platform.observer;

import learning_platform.entities.Enrollment;

public class CertificateIssuer implements ProgressObserver{
    @Override
    public void onCourseCompleted(Enrollment enrollment) {
        System.out.println("--- OBSERVER (CertificateIssuer) ---");
        System.out.println("Issuing certificate to " + enrollment.getStudent().getName() +
                " for completing '" + enrollment.getCourse().getTitle() + "'.");
        System.out.println("------------------------------------");
    }
}

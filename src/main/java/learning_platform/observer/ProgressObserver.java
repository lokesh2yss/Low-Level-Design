package learning_platform.observer;

import learning_platform.entities.Enrollment;

public interface ProgressObserver {
    void onCourseCompleted(Enrollment enrollment);
}

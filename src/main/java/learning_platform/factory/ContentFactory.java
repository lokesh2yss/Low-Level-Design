package learning_platform.factory;

import learning_platform.composite.CourseComponent;
import learning_platform.composite.Lecture;
import learning_platform.composite.Quiz;

import java.util.UUID;

public class ContentFactory {
    public static CourseComponent createLecture(String title, int duration) {
        return new Lecture(UUID.randomUUID().toString(), title, duration);
    }
    public static CourseComponent createQuiz(String title, int courseCount) {
        return new Quiz(UUID.randomUUID().toString(), title, courseCount);
    }
}

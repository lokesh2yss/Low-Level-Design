package learning_platform.composite;

public class Quiz implements CourseComponent{
    private final String id;
    private final String title;
    private final int questionCount;

    public Quiz(String id, String title, int questionCount) {
        this.id = id;
        this.title = title;
        this.questionCount = questionCount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void display() {
        System.out.println("  - Quiz: " + title + " (" + questionCount + " questions)");
    }
}

package learning_platform.composite;

public class Lecture implements CourseComponent{
    private final String id;
    private final String title;
    private final int durationMinutes;

    public Lecture(String id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.durationMinutes = duration;
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
        System.out.println("  - Lecture: " + title + " (" + durationMinutes + " mins)");
    }
}

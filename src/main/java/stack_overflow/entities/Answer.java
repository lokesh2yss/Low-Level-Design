package stack_overflow.entities;


import java.util.UUID;

public class Answer extends Post {
    private boolean isAccepted;
    public Answer(String body, User author) {
        super(UUID.randomUUID().toString(), body, author);
    }
    public void setAccepted(boolean accepted) {
        this.isAccepted = accepted;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}

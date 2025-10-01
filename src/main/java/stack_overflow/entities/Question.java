package stack_overflow.entities;

import stack_overflow.enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Question extends Post{
    private final String title;
    private final Set<Tag> tags;
    private final List<Answer> answers = new ArrayList<>();
    private Answer acceptedAnswer;
    public Question(String title, String body, User author, Set<Tag> tags) {
        super(UUID.randomUUID().toString(), body, author);
        this.title = title;
        this.tags = tags;

    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
    public void acceptAnswer(Answer answer) {
        if(!this.author.getId().equals(answer.getAuthor().getId()) && acceptedAnswer == null) {
            this.acceptedAnswer = answer;
            answer.setAccepted(true);
            notifyObservers(new Event(EventType.ACCEPT_ANSWER, answer.getAuthor(), answer));
        }


    }
    @Override
    public String getId() {
        return super.getId();
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return super.getBody();
    }
}

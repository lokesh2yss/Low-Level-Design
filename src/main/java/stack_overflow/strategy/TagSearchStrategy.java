package stack_overflow.strategy;

import stack_overflow.entities.Question;
import stack_overflow.entities.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class TagSearchStrategy implements SearchStrategy{
    private final Tag tag;
    public TagSearchStrategy(Tag tag) {
        this.tag = tag;
    }
    @Override
    public List<Question> filter(List<Question> questions) {
        return questions.stream()
                .filter(question -> question.getTags().stream()
                        .anyMatch(tag1 -> tag1.getName().equalsIgnoreCase(tag.getName())))
                .collect(Collectors.toList());
    }
}

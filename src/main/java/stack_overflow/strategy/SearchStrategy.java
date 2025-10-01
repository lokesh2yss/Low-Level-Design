package stack_overflow.strategy;

import stack_overflow.entities.Question;

import java.util.List;

public interface SearchStrategy {
    List<Question> filter(List<Question> questions);
}

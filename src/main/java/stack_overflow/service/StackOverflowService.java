package stack_overflow.service;

import stack_overflow.entities.*;
import stack_overflow.enums.VoteType;
import stack_overflow.strategy.SearchStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflowService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Question> questions = new ConcurrentHashMap<>();
    private final Map<String, Tag> tags = new ConcurrentHashMap<>();
    private final Map<String, Answer> answers = new ConcurrentHashMap<>();
    private final ReputationManager reputationManager = new ReputationManager();
    public User createUser(String name) {
        User user = new User(name);
        users.put(user.getId(), user);
        return user;
    }
    public Question postQuestion(String userId, String title, String body, Set<Tag> tags) {
        User author = users.get(userId);
        if(author == null) throw new IllegalArgumentException("User not found with iD:" + userId);
        Question question = new Question(title, body, author, tags);
        question.addObserver(reputationManager);
        questions.put(question.getId(), question);
        return question;
    }
    public Answer postAnswer(String userId, String questionId, String body) {
        User author = users.get(userId);
        if(author == null) throw new IllegalArgumentException("User not found with id: "+userId);
        Question question = questions.get(questionId);
        if(question == null) throw new IllegalArgumentException("Question not found with id: "+questionId);
        Answer answer = new Answer(body, author);
        answer.addObserver(reputationManager);
        question.addAnswer(answer);
        answers.put(answer.getId(), answer);
        return answer;
    }public void voteOnPost(String userId, String postId, VoteType voteType) {
        User voter = users.get(userId);
        if(voter == null) throw new IllegalArgumentException("User not found with id: "+userId);
        Post post = findPostById(postId);
        post.vote(voter, voteType);

    }
    public void acceptAnswer(String questionId, String answerId) {
        Question question = questions.get(questionId);
        Answer answer = answers.get(answerId);
        question.acceptAnswer(answer);
        answer.setAccepted(true);
    }
    public List<Question> searchQuestions(List<SearchStrategy> strategies) {
        List<Question> questions1 = new ArrayList<>(questions.values());
        for(SearchStrategy strategy: strategies) {
            questions1 = strategy.filter(questions1);
        }
        return questions1;
    }
    public User getUser(String userId) {
        return users.get(userId);
    }

    private Post findPostById(String postId) {
        Post post = null;
        post = questions.get(postId);
        if(post == null) {
            post = answers.get(postId);
        }
        if(post != null) return post;
        throw new NoSuchElementException("Post not found");
    }

}

package social_network.services;

import social_network.entities.Comment;
import social_network.entities.Post;
import social_network.entities.User;
import social_network.observer.PostObserver;
import social_network.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = PostRepository.getInstance();
    private final List<PostObserver> observers = new ArrayList<>();
    public void addObserver(PostObserver observer) {
        observers.add(observer);
    }
    public Post createPost(User author, String content) {
        Post post = new Post(author, content);
        postRepository.save(post);
        author.addPost(post);
        observers.forEach(observer -> observer.onPostCreated(post));
        return post;
    }
    public void likePost(User user, String postId) {
        Post post = postRepository.findPostById(postId);
        post.addLike(user);
        observers.forEach(observer -> observer.onLike(post,user));
    }
    public void addComment(User author, String commentableId, String content) {
        Comment comment = new Comment(author,content);
        Post post = postRepository.findPostById(commentableId);
        post.addComment(comment);
        observers.forEach(observer -> observer.onComment(post, comment));
    }

}

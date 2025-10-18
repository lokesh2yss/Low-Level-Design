package social_network.repositories;

import social_network.entities.Post;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepository {
    private static final PostRepository instance = new PostRepository();
    private final Map<String, Post> posts = new ConcurrentHashMap<>();
    private PostRepository() {}

    public static PostRepository getInstance() {
        return instance;
    }
    public void save(Post post) {
        posts.put(post.getId(), post);
    }
    public Post findPostById(String id) {
        return posts.get(id);
    }
}

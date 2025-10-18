package social_network;

import social_network.entities.Post;
import social_network.entities.User;
import social_network.observer.UserNotifier;
import social_network.services.NewsFeedService;
import social_network.services.PostService;
import social_network.services.UserService;

import java.util.List;

public class SocialNetworkFacade {
    private final UserService userService;
    private final PostService postService;
    private final NewsFeedService newsFeedService;
    public SocialNetworkFacade() {
        this.userService = new UserService();
        this.postService = new PostService();
        this.newsFeedService = new NewsFeedService();
        postService.addObserver(new UserNotifier());
    }
    public User createUser(String name, String email) {
        return userService.createUser(name, email);
    }
    public void addFriend(String userId1, String userId2) {
        userService.addFriend(userId1, userId2);
    }
    public Post createPost(String authorId, String content) {
        User author = userService.gerUserById(authorId);
        return postService.createPost(author, content);
    }
    public void addComment(String userId, String postId, String content) {
        User author = userService.gerUserById(userId);
        postService.addComment(author, postId, content);
    }
    public void likePost(String userId, String postId) {
        User user = userService.gerUserById(userId);
        postService.likePost(user, postId);
    }
    public List<Post> getNewsFeed(String userId) {
        return newsFeedService.getNewsFeed(userService.gerUserById(userId));
    }
}

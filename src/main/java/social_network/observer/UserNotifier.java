package social_network.observer;

import social_network.entities.Comment;
import social_network.entities.Post;
import social_network.entities.User;

import java.util.Set;

public class UserNotifier implements PostObserver{
    @Override
    public void onPostCreated(Post post) {
        User author = post.getAuthor();
        Set<User> friends = author.getFriends();
        for(User user: friends) {
            System.out.println("Notification for " + user.getName() + ": " + author.getName() + " created a new post: " + post.getContent());
        }
    }

    @Override
    public void onLike(Post post, User user) {
        User author = post.getAuthor();
        System.out.println("Notification for " + author.getName() + ": " + user.getName() + " liked your post");
    }

    @Override
    public void onComment(Post post, Comment comment) {
        User author = post.getAuthor();
        System.out.println("Notification for " + author.getName() + ": " + comment.getAuthor().getName() + " commented on your post");
    }
}

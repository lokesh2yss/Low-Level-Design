package social_network.observer;

import social_network.entities.Comment;
import social_network.entities.Post;
import social_network.entities.User;

public interface PostObserver {
    void onPostCreated(Post post);
    void onLike(Post post, User user);
    void onComment(Post post, Comment comment);
}

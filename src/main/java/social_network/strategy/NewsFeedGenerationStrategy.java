package social_network.strategy;

import social_network.entities.Post;
import social_network.entities.User;

import java.util.List;

public interface NewsFeedGenerationStrategy {
    List<Post> generateFeed(User user);
}

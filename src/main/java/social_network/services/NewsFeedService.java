package social_network.services;

import social_network.entities.Post;
import social_network.entities.User;
import social_network.strategy.ChronologicalStrategy;
import social_network.strategy.NewsFeedGenerationStrategy;

import java.util.List;

public class NewsFeedService {
    private NewsFeedGenerationStrategy strategy;
    public NewsFeedService() {
        strategy = new ChronologicalStrategy();
    }

    public void setStrategy(NewsFeedGenerationStrategy strategy) {
        this.strategy = strategy;
    }
    public List<Post> getNewsFeed(User user) {
        return strategy.generateFeed(user);
    }
}

package social_network.strategy;

import social_network.entities.Post;
import social_network.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ChronologicalStrategy implements NewsFeedGenerationStrategy{
    @Override
    public List<Post> generateFeed(User user) {
        List<Post> feed = new ArrayList<>();
        for(User friend: user.getFriends()) {
            feed.addAll(friend.getPosts());
        }
        // Sort posts by timestamp in reverse (most recent first)
        feed.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()));
        return feed;
    }
}

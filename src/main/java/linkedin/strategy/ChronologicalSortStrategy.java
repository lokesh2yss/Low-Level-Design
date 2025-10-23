package linkedin.strategy;

import linkedin.entities.Post;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChronologicalSortStrategy implements FeedSortingStrategy{
    @Override
    public List<Post> sort(List<Post> posts) {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed()).collect(Collectors.toList());
    }
}

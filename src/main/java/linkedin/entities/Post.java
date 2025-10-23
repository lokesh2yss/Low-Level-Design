package linkedin.entities;

import linkedin.enums.NotificationType;
import linkedin.observer.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post extends Subject {
    private final String id;
    private final String content;
    private final Member author;
    private final LocalDateTime createdAt;
    private final List<Like> likes = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    public Post(String content, Member author) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        // The author should be notified of interactions with their own post
        this.addObserver(author);
    }
    public void addLike(Member member) {
        likes.add(new Like(member));
        String notificationContent = member.getName() +" liked your post";
        Notification notification = new Notification(author.getId(), NotificationType.POST_LIKE, notificationContent);
        notifyObservers(notification);
    }
    public void addComment(Member member, String text) {
        comments.add(new Comment(member, text));
        String notificationContent = member.getName() + " commented on your post: \"" + text + "\"";
        Notification notification = new Notification(author.getId(), NotificationType.POST_COMMENT, notificationContent);
        notifyObservers(notification);
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Member getAuthor() {
        return author;
    }
}

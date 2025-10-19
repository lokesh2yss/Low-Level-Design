package learning_platform.repositories;

import learning_platform.entities.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private UserRepository() {}
    public static UserRepository getInstance() {
        return instance;
    }
    public void save(User user) {
        users.put(user.getId(), user);
    }
    public User findById(String id) {
        return users.get(id);
    }
}

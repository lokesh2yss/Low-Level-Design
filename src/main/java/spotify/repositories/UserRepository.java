package spotify.repositories;


import spotify.entities.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private UserRepository() {

    }
    public static UserRepository getInstance() {
        return instance;
    }
    public void save(User user) {
        this.users.put(user.getId(), user);
    }
    public User findUserById(String id) {
        return this.users.get(id);
    }
}

package social_network.services;

import social_network.entities.User;
import social_network.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository = UserRepository.getInstance();

    public User createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }
    public void addFriend(String userId1, String userId2) {
        User user1 = userRepository.findUserById(userId1);
        User user2 = userRepository.findUserById(userId2);
        user1.addFriend(user2);
        user2.addFriend(user1);
    }
    public User gerUserById(String userId) {
        return userRepository.findUserById(userId);
    }
}

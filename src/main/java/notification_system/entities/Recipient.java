package notification_system.entities;

import java.util.Optional;

public class Recipient {
    private final String userId;
    private final Optional<String> email;
    private final Optional<String> phoneNumber;
    private final Optional<String> pushToken;

    public Recipient(String userIdd, String email, String phoneNumber, String pushToken) {
        this.userId = userIdd;
        this.email = Optional.ofNullable(email);
        this.phoneNumber = Optional.ofNullable(phoneNumber);
        this.pushToken = Optional.ofNullable(pushToken);
    }

    public Optional<String> getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }

    public Optional<String> getPushToken() {
        return pushToken;
    }
}

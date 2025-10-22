package spotify.entities;

import java.util.UUID;

public abstract class AbstractUser {
    protected final String id;
    protected final String name;
    protected final String email;

    public AbstractUser(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

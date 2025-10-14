package url_shortener.strategy;

import java.util.UUID;

public class UUIDStrategy implements KeyGenerationStrategy{
    private static final int KEY_LENGTH = 6;
    @Override
    public String generateKey(long id) {
        // Generate a new UUID, remove the hyphens, and take a substring.
        String uuid = UUID.randomUUID().toString().replace("-","");
        // Return the first part of the UUID.

        return uuid.substring(0, KEY_LENGTH);
    }
}

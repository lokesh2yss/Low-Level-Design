package url_shortener.strategy;

import java.util.Random;

public class RandomStrategy implements KeyGenerationStrategy{
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int KEY_LENGTH = 6;
    private final Random random = new Random();
    @Override
    public String generateKey(long id) {
        StringBuilder sb = new StringBuilder();
        for(int i =0; i< KEY_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}

package url_shortener.strategy;

public interface KeyGenerationStrategy {
    String generateKey(long id);
}

package rate_limiter.strategy;

public interface RateLimitingStrategy {
    boolean allowRequest(String userId);
}

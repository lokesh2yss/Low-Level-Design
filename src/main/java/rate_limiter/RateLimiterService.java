package rate_limiter;

import payment_gateway.entities.PaymentResponse;
import rate_limiter.strategy.RateLimitingStrategy;

public class RateLimiterService {
    private static RateLimiterService instance;
    private RateLimitingStrategy rateLimitingStrategy;
    private RateLimiterService() {}

    public static synchronized RateLimiterService getInstance() {
        if(instance == null) {
            instance = new RateLimiterService();
        }
        return instance;
    }

    public void setRateLimitingStrategy(RateLimitingStrategy rateLimitingStrategy) {
        this.rateLimitingStrategy = rateLimitingStrategy;
    }
    public void handleRequest(String userId) {
        if(rateLimitingStrategy.allowRequest(userId)) {
            System.out.println("Request from user " + userId + " is allowed");
        } else {
            System.out.println("Request from user " + userId + " is rejected: Rate limit exceeded");
        }
    }
}

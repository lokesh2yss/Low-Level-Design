package rate_limiter;

import rate_limiter.strategy.FixedWindowStrategy;
import rate_limiter.strategy.RateLimitingStrategy;
import rate_limiter.strategy.TokenBucketStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterDemo {
    public static void main(String[] args) {
        String userId = "user123";

        System.out.println("=== Fixed Window Demo ===");
        runFixedWindowDemo(userId);

        System.out.println("\n=== Token Bucket Demo ===");
        runTokenBucketDemo(userId);
    }
    private static void runFixedWindowDemo(String userId) {
        int maxRequests = 5;
        int windowSeconds = 10;

        RateLimitingStrategy fixedWindowStrategy = new FixedWindowStrategy(maxRequests, windowSeconds);
        RateLimiterService service = RateLimiterService.getInstance();
        service.setRateLimitingStrategy(fixedWindowStrategy);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> service.handleRequest(userId));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }

    private static void runTokenBucketDemo(String userId) {
        int capacity = 5;
        int refillRate = 1; // 1 token per second

        RateLimitingStrategy tokenBucketStrategy = new TokenBucketStrategy(capacity, refillRate);
        RateLimiterService service = RateLimiterService.getInstance();
        service.setRateLimitingStrategy(tokenBucketStrategy);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Simulate 10 rapid requests
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> service.handleRequest(userId));
            try {
                Thread.sleep(300); // faster than refill rate
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }

}

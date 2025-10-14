package rate_limiter.strategy;

import payment_gateway.entities.PaymentResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketStrategy implements RateLimitingStrategy{
    private final int capacity;
    private final int refillRatePerSecond;
    private final Map<String, TokenBucket> userBuckets = new ConcurrentHashMap<>();

    public TokenBucketStrategy(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userBuckets.putIfAbsent(userId, new TokenBucket(capacity, refillRatePerSecond, currentTime));
        TokenBucket bucket = userBuckets.get(userId);
        synchronized (bucket) {
            bucket.refill(currentTime);
            if(bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            }
            else {
                return false;
            }
        }
    }
    private static class TokenBucket {
        int tokens;
        final int capacity;
        final int refillRatePerSecond;
        long lastRefillTimestamp;
        public TokenBucket(int capacity, int refillRatePerSecond, long currentTimeMillis) {
            this.tokens = capacity;
            this.capacity = capacity;
            this.refillRatePerSecond = refillRatePerSecond;
            this.lastRefillTimestamp = currentTimeMillis;
        }
        public void refill(long currentTime) {
            long elapsedTime = currentTime = lastRefillTimestamp;
            int tokensToAdd = (int) ((elapsedTime/1000.0)*refillRatePerSecond);
            if(tokensToAdd > 0) {
                tokens = Math.min(capacity, tokensToAdd + tokens);
                lastRefillTimestamp = currentTime;
            }
        }
    }

}

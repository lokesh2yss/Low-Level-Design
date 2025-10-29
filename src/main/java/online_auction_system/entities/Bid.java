package online_auction_system.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bid implements Comparable<Bid>{
    private final User bidder;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public Bid(User bidder, BigDecimal amount) {
        this.bidder = bidder;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public User getBidder() {
        return bidder;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Bid o) {
        int amountComparison = this.amount.compareTo(o.amount);
        if(amountComparison != 0) return amountComparison;
        return o.timestamp.compareTo(this.timestamp);
    }
    @Override
    public String toString() {
        return String.format("Bidder: %s, Amount: %.2f, Time: %s", bidder.getName(), amount, timestamp);
    }
}

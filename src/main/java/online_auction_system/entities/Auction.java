package online_auction_system.entities;

import online_auction_system.observer.AuctionObserver;
import online_auction_system.state.ActiveState;
import online_auction_system.state.AuctionState;
import online_auction_system.state.PendingState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Auction {
    private final String id;
    private final String itemName;
    private final String description;
    private final BigDecimal startingPrice;
    private final LocalDateTime endTime;

    private final List<Bid> bids;
    private final Set<AuctionObserver> observers;
    private AuctionState state;
    private Bid winningBid;
    public Auction(String itemName, String description, BigDecimal startingPrice, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.description = description;
        this.startingPrice = startingPrice;
        this.endTime = endTime;

        this.bids = new ArrayList<>();
        this.observers = ConcurrentHashMap.newKeySet();// Thread-safe set
        this.state = new PendingState();
    }
    public void startAuction() {
        state.startAuction(this);
    }
    public synchronized void placeBid(User bidder, BigDecimal amount) {
        state.placeBid(this, bidder, amount);
    }
    public synchronized void endAuction() {
        state.endAuction(this);
    }
    public void cancelAuction() {
        state.cancelAuction(this);
    }
    public Bid getHighestBid() {
        if(bids.isEmpty()) {
            return null;
        }
        return Collections.max(bids);
    }
    public void addBid(Bid bid) {
        bids.add(bid);
    }
    public boolean isActive() {
        return state instanceof ActiveState;
    }
    public void addObserver(AuctionObserver observer) {
        if (!observers.contains(observer)) observers.add(observer);
    }

    public void notifyObserver(AuctionObserver observer, String message) {
        observer.onUpdate(this, message);
    }

    public void setState(AuctionState newState) {
        this.state = newState;
    }
    public AuctionState getState() {
        return state;
    }
    public void notifyAllObservers(String message) {
        for(AuctionObserver observer: observers) {
            observer.onUpdate(this, message);
        }
    }

    public String getId() {
        return id;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public Bid getWinningBid() {
        return winningBid;
    }
    public List<Bid> getBidHistory() { return Collections.unmodifiableList(bids); }

    public String getItemName() {
        return itemName;
    }
}

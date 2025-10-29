package online_auction_system.services;

import online_auction_system.entities.Auction;
import online_auction_system.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AuctionService {
    private static volatile AuctionService instance;
    private final Map<String, User> users;
    private final Map<String, Auction> auctions;
    private final ScheduledExecutorService scheduler;

    private AuctionService() {
        users = new ConcurrentHashMap<>();
        auctions = new ConcurrentHashMap<>();
        scheduler = Executors.newScheduledThreadPool(1);
    }

    public static AuctionService getInstance() {
        if (instance == null) {
            synchronized (AuctionService.class) {
                if (instance == null) {
                    instance = new AuctionService();
                }
            }
        }
        return instance;
    }

    public User createUser(String name) {
        User user = new User(name);
        users.put(user.getId(), user);
        return user;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public Auction createAuction(String itemName, String description, BigDecimal startingPrice, LocalDateTime endTime) {
        Auction auction = new Auction(itemName, description, startingPrice, endTime);
        auctions.put(auction.getId(), auction);
        // In a real system, you'd use a scheduler to automatically end auctions.
        // This demonstrates how it would be done
        long delay = java.time.Duration.between(LocalDateTime.now(), endTime).toMillis();
        scheduler.schedule(() -> endAuction(auction.getId()), delay, TimeUnit.MILLISECONDS);
        System.out.printf("New auction created for '%s' (ID: %s), ending at %s.\n", itemName, auction.getId(), endTime);
        return auction;
    }
    public List<Auction> viewActiveAuctions() {
        return auctions.values()
                .stream()
                .filter(Auction::isActive)
                .collect(Collectors.toList());
    }
    public void placeBid(String auctionId, String bidderId, BigDecimal amount) {
        Auction auction = getAuction(auctionId);
        auction.placeBid(users.get(bidderId), amount);
    }
    public void endAuction(String auctionId) {
        Auction auction = getAuction(auctionId);
        auction.endAuction();
    }
    public Auction getAuction(String auctionId) {
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new NoSuchElementException("Auction with ID " + auctionId + " not found.");
        }
        return auction;
    }
    public void shutdown() {
        scheduler.shutdown();
        try {
            if(!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
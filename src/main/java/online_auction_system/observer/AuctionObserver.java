package online_auction_system.observer;

import online_auction_system.entities.Auction;

public interface AuctionObserver {
    void onUpdate(Auction auction, String message);
}

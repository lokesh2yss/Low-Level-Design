package online_auction_system.state;

import online_auction_system.entities.Auction;
import online_auction_system.entities.User;

import java.math.BigDecimal;

public interface AuctionState {
    void startAuction(Auction auction);
    void placeBid(Auction auction, User bidder, BigDecimal amount);
    void endAuction(Auction auction);
    void cancelAuction(Auction auction);
    String getName();
}

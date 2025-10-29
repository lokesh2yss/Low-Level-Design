package online_auction_system.state;

import online_auction_system.entities.Auction;
import online_auction_system.entities.User;

import java.math.BigDecimal;

public class PendingState implements AuctionState{
    @Override
    public void startAuction(Auction auction) {
        auction.setState(new ActiveState());
        System.out.println("Auction started: " + auction.getItemName());
    }

    @Override
    public void placeBid(Auction auction, User bidder, BigDecimal amount) {
        throw new IllegalStateException("Auction not started yet.");
    }

    @Override
    public void endAuction(Auction auction) {
        throw new IllegalStateException("Cannot end before starting.");
    }

    @Override
    public void cancelAuction(Auction auction) {
        auction.setState(new CancelledState());
        System.out.println("Auction cancelled before start.");
    }

    @Override
    public String getName() {
        return "PENDING";
    }
}

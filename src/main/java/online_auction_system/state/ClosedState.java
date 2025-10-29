package online_auction_system.state;

import online_auction_system.entities.Auction;
import online_auction_system.entities.User;

import java.math.BigDecimal;

public class ClosedState implements AuctionState{
    @Override
    public void startAuction(Auction auction) {
        throw new IllegalStateException("Cannot restart a closed auction.");
    }

    @Override
    public void placeBid(Auction auction, User bidder, BigDecimal amount) {
        throw new IllegalStateException("Cannot place bid on closed auction.");
    }

    @Override
    public void endAuction(Auction auction) {
        System.out.println("Auction already closed.");
    }

    @Override
    public void cancelAuction(Auction auction) {
        throw new IllegalStateException("Cannot cancel after closing.");
    }

    @Override
    public String getName() {
        return "CLOSED";
    }
}

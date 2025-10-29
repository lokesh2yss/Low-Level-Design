package online_auction_system.state;

import online_auction_system.entities.Auction;
import online_auction_system.entities.User;

import java.math.BigDecimal;

public class CancelledState implements AuctionState{
    @Override
    public void startAuction(Auction auction) {
        throw new IllegalStateException("Cancelled auction cannot be started.");
    }

    @Override
    public void placeBid(Auction auction, User bidder, BigDecimal amount) {
        throw new IllegalStateException("Cannot bid on a cancelled auction.");
    }

    @Override
    public void endAuction(Auction auction) {
        throw new IllegalStateException("Cancelled auction cannot be closed.");
    }

    @Override
    public void cancelAuction(Auction auction) {
        System.out.println("Auction already cancelled.");
    }

    @Override
    public String getName() {
        return "CANCELLED";
    }
}

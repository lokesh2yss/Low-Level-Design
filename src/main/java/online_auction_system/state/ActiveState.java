package online_auction_system.state;

import online_auction_system.entities.Auction;
import online_auction_system.entities.Bid;
import online_auction_system.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ActiveState implements AuctionState{
    @Override
    public void startAuction(Auction auction) {
        throw new IllegalStateException("Auction already active.");
    }

    @Override
    public void placeBid(Auction auction, User bidder, BigDecimal amount) {
        if(LocalDateTime.now().isAfter(auction.getEndTime())) {
            auction.setState(new ClosedState());
            throw new IllegalStateException("Auction already ended");
        }
        Bid highest = auction.getHighestBid();
        if(highest != null && amount.compareTo(highest.getAmount()) <=0) {
            throw new IllegalArgumentException("Bid amount must be larger than the current highest bidder's bid");
        }
        auction.addBid(new Bid(bidder, amount));
        auction.addObserver(bidder);
        if(highest != null) {
            auction.notifyObserver(highest.getBidder(), "You have been outbid by " + bidder.getName() + " (" + amount + ")");
        }
        System.out.println("Bid placed successfully by " + bidder.getName() + " for " + amount);
    }

    @Override
    public void endAuction(Auction auction) {
        auction.setState(new ClosedState());
        System.out.printf("Auction %s successfully ended", auction.getItemName());
        System.out.println("Auction closed. Winner: " + auction.getHighestBid().getBidder().getName());
    }

    @Override
    public void cancelAuction(Auction auction) {
        auction.setState(new CancelledState());
        System.out.println("Auction cancelled while active.");
    }

    @Override
    public String getName() {
        return "ACTIVE";
    }
}

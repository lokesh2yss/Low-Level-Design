package online_auction_system;

import online_auction_system.entities.Auction;
import online_auction_system.entities.User;
import online_auction_system.services.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OnlineAuctionSystemDemo {
    public static void main(String[] args) {
        AuctionService auctionService = AuctionService.getInstance();

        // Create users
        User alice = auctionService.createUser("Alice");
        User bob = auctionService.createUser("Bob");
        User carol = auctionService.createUser("Carol");

        System.out.println("=============================================");
        System.out.println("        Online Auction System Demo           ");
        System.out.println("=============================================");

        // 1. Create an auction that will last for a short duration
        LocalDateTime endTime = LocalDateTime.now().plusSeconds(10);
        Auction laptopAuction = auctionService.createAuction(
                "Vintage Laptop",
                "A rare 1990s laptop, in working condition.",
                new BigDecimal("100.00"),
                endTime
        );

        System.out.println("\nAuction created with ID: " + laptopAuction.getId());
        System.out.println("Auction initial state: " + laptopAuction.getState().getName());
        System.out.println();

        // 2. Start the auction (transition: PENDING → ACTIVE)
        System.out.println("Starting auction...");
        laptopAuction.startAuction();
        System.out.println("Auction current state: " + laptopAuction.getState().getName());
        System.out.println();

        // 3. Bidding war starts
        try {
            auctionService.placeBid(laptopAuction.getId(), alice.getId(), new BigDecimal("110.00"));
            Thread.sleep(500); // Simulate time passing

            auctionService.placeBid(laptopAuction.getId(), bob.getId(), new BigDecimal("120.00")); // Alice gets outbid
            Thread.sleep(500);

            auctionService.placeBid(laptopAuction.getId(), carol.getId(), new BigDecimal("125.00")); // Bob gets outbid
            Thread.sleep(500);

            auctionService.placeBid(laptopAuction.getId(), alice.getId(), new BigDecimal("150.00")); // Carol gets outbid

            // 4. Wait for the auction to end automatically (simulated)
            System.out.println("\n--- Waiting for auction to end automatically... ---");
            Thread.sleep(12 * 1000); // wait beyond auction end time

            // 5. Manually close auction (transition: ACTIVE → CLOSED)
            //laptopAuction.endAuction();

        } catch (Exception e) {
            System.err.println("An error occurred during bidding: " + e.getMessage());
        }

        // 6. Post-auction summary
        System.out.println("\n--- Post-Auction Information ---");
        Auction endedAuction = auctionService.getAuction(laptopAuction.getId());

        if (endedAuction.getHighestBid() != null) {
            System.out.printf("Final Winner: %s%n", endedAuction.getHighestBid().getBidder().getName());
            System.out.printf("Winning Price: $%.2f%n", endedAuction.getHighestBid().getAmount());
        } else {
            System.out.println("The auction ended with no winner.");
        }

        System.out.println("\nFull Bid History:");
        endedAuction.getBidHistory().forEach(System.out::println);

        // 7. Try to place bid after closure (should throw exception via ClosedState)
        System.out.println("\n--- Attempting to bid on an ended auction ---");
        try {
            auctionService.placeBid(laptopAuction.getId(), bob.getId(), new BigDecimal("200.00"));
        } catch (IllegalStateException e) {
            System.out.println("CAUGHT EXPECTED ERROR: " + e.getMessage());
        }

        // 8. Shut down background tasks if any
        auctionService.shutdown();
    }
}

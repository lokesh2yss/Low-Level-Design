package library_management_system.state;

import library_management_system.entities.BookCopy;
import library_management_system.observer.Member;
import library_management_system.service.TransactionService;

public class OnHoldState implements ItemState{
    @Override
    public void checkout(BookCopy copy, Member member) {
        // Only a member who placed the hold can check it out.
        if(copy.getItem().isObserver(member)) {
            TransactionService.getInstance().createLoan(copy, member);
            copy.getItem().removeObserver(member); //// Remove from waiting list
            copy.setState(new CheckedOutState());
            System.out.println("Hold fulfilled. " + copy.getId() + " checked out by " + member.getName());
        }
        else {
            System.out.println("This item is on hold for another member.");
        }
    }

    @Override
    public void returnItem(BookCopy copy) {
        System.out.println("Invalid action. Item is on hold, not checked out.");
    }

    @Override
    public void placeHold(BookCopy copy, Member member) {
        System.out.println("Item is already on hold.");
    }
}

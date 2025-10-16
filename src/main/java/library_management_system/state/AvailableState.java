package library_management_system.state;

import library_management_system.entities.BookCopy;
import library_management_system.observer.Member;
import library_management_system.service.TransactionService;

public class AvailableState implements ItemState{
    @Override
    public void checkout(BookCopy copy, Member member) {
        TransactionService.getInstance().createLoan(copy, member);
        copy.setState(new CheckedOutState());
        System.out.println(copy.getId() + " checked out by " + member.getName());
    }

    @Override
    public void returnItem(BookCopy copy) {
        System.out.println("Cannot return an item that is already available.");
    }

    @Override
    public void placeHold(BookCopy copy, Member member) {
        System.out.println("Cannot place hold on an available item. Please check it out.");
    }
}

package library_management_system.state;

import library_management_system.entities.BookCopy;
import library_management_system.observer.Member;

public interface ItemState {
    void checkout(BookCopy copy, Member member);
    void returnItem(BookCopy copy);
    void placeHold(BookCopy copy, Member member);
}

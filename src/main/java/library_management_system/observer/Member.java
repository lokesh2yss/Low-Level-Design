package library_management_system.observer;

import library_management_system.entities.Loan;
import library_management_system.observer.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String id;
    private final String name;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
    // Observer update method
    public void update(LibraryItem item) {
        System.out.println("Notification for" +name+"The book: "+item.getTitle()+" you place on a hold is not available");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Loan> getLoans() {
        return loans;
    }
    public void addLoan(Loan loan) {
        loans.add(loan);
    }
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }
}

package library_management_system.service;

import library_management_system.entities.BookCopy;
import library_management_system.entities.Loan;
import library_management_system.observer.Member;

import java.util.HashMap;
import java.util.Map;

public class TransactionService {
    private static final TransactionService instance = new TransactionService();
    private final Map<String, Loan> activeLoans = new HashMap<>();

    private TransactionService() {}
    public static TransactionService getInstance() {
        return instance;
    }
    public void createLoan(BookCopy copy, Member member) {
        if(activeLoans.containsKey(copy.getId())) {
            throw new IllegalArgumentException("This copy is already on loan");
        }
        Loan loan = new Loan(copy, member);
        activeLoans.put(copy.getId(), loan);
        member.addLoan(loan);
    }
    public void endLoan(BookCopy copy) {
        Loan loan = activeLoans.remove(copy.getId());
        if(loan != null) {
            loan.getMember().removeLoan(loan);
        }
    }
}

package atm.entities;

import atm.chain_of_responsibility.*;
import atm.enums.OperationType;
import atm.state.ATMState;
import atm.state.IdleState;


import java.util.concurrent.atomic.AtomicLong;

public class ATM {
    private static ATM instance;
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private static final AtomicLong transactionCounter = new AtomicLong();
    private ATMState currentState;
    private Card currentCard;
    private ATM() {
        this.currentState = new IdleState();
        this.bankService = new BankService();

        DispenseChain c1 = new NoteDispenser100(10);
        DispenseChain c2 = new NoteDispenser50(20);
        DispenseChain c3 = new NoteDispenser20(30);

        c1.setNextChain(c2);
        c2.setNextChain(c3);
        this.cashDispenser = new CashDispenser(c1);
    }

    public static ATM getInstance() {
        if(instance == null) {
            instance = new ATM();
        }
        return instance;
    }
    public void changeState(ATMState newState) {
        this.currentState = newState;
    }
    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }
    public void insertCard(String cardNumber) {
        this.currentState.insertCard(this, cardNumber);
    }
    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }
    public void selectOperation(OperationType op, int...args) {
        currentState.selectOperation(this, op, args);
    }
    public void checkBalance() {
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your account balance is: $%.2f%n", balance);
    }
    public void withdrawCash(int amount) {
        if(!cashDispenser.canDispenseCash(amount)) {
            System.out.println("Insufficient cash in the ATM machine");
        }
        bankService.withdrawMoney(currentCard, amount);
        try {
            cashDispenser.dispenseCash(amount);
        }
        catch (Exception e) {
            bankService.depositMoney(currentCard, amount); //deposit money back into the account
        }
    }
    public void depositCash(int amount) {
        bankService.depositMoney(currentCard, amount);
    }
    public Card getCurrentCard() {
        return currentCard;
    }
    public BankService getBankService() {
        return bankService;
    }
}

package atm.state;

import atm.entities.ATM;
import atm.enums.OperationType;

public class AuthenticatedState implements ATMState{
    @Override
    public void insertCard(ATM atm, String cardNumber) {
        System.out.println("Card is already inserted, please select an operation");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("Card is already authenticated with a valid PIN. Please select an operation");
    }

    @Override
    public void selectOperation(ATM atm, OperationType op, int... args) {
        switch (op) {
            case CHECK_BALANCE -> {
              atm.checkBalance();
            }
            case WITHDRAW_CASH -> {
                if(args.length == 0 || args[0] < 0) {
                    System.out.println("Error: Invalid withdrawal amount specified.");
                }
                int amountToWithdraw = args[0];
                double accountBalance = atm.getBankService().getBalance(atm.getCurrentCard());
                if(amountToWithdraw > accountBalance) {
                    System.out.println("Error: Insufficient balance.");
                    break;
                }
                System.out.println("Processing withdrawal for $" + amountToWithdraw);
                atm.withdrawCash(amountToWithdraw);
            }
            case DEPOSIT_CASH -> {
                if (args.length == 0 || args[0] <= 0) {
                    System.out.println("Error: Invalid deposit amount specified.");
                    break;
                }
                int amountToDeposit = args[0];
                System.out.println("Processing deposit for $" + amountToDeposit);
                atm.depositCash(amountToDeposit);
            }
            default -> {
                System.out.println("Invalid operation selected");
            }
        }
        // End the session after one transaction
        System.out.println("Transaction complete.");
        ejectCard(atm);
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Ending session. Card has been ejected. Thank you for using our ATM.");
        atm.setCurrentCard(null);
        atm.changeState(new IdleState());
    }
}

package atm.state;

import atm.entities.ATM;
import atm.enums.OperationType;

public interface ATMState {
    void insertCard(ATM atm, String cardNumber);
    void enterPin(ATM atm, String pin);
    void selectOperation(ATM atm, OperationType op, int...args);
    void ejectCard(ATM atm);
}

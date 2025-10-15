package vending_machine.state;

import vending_machine.entities.VendingMachine;
import vending_machine.enums.Coin;

public class HasMoneyState extends VendingMachineState{
    public HasMoneyState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Already received enough money");
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected");
    }

    @Override
    public void dispense() {
        machine.setState(new DispensingState(machine));
        machine.dispenseItem();
    }

    @Override
    public void refund() {
        machine.refundBalance();
        machine.reset();
        machine.setState(new IdleState(machine));
    }
}

package vending_machine.state;

import vending_machine.entities.VendingMachine;
import vending_machine.enums.Coin;

public abstract class VendingMachineState {
    VendingMachine machine;
    public VendingMachineState(VendingMachine machine) {
        this.machine = machine;
    }
    public abstract void insertCoin(Coin coin);
    public abstract void selectItem(String code);
    public abstract void dispense();
    public abstract void refund();
}

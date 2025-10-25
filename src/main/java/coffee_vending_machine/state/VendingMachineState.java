package coffee_vending_machine.state;

import coffee_vending_machine.CoffeeVendingMachine;
import coffee_vending_machine.entities.Coffee;

public interface VendingMachineState {
    void selectCoffee(CoffeeVendingMachine machine, Coffee coffee);
    void insertMoney(CoffeeVendingMachine machine, int amount);
    void dispenseCoffee(CoffeeVendingMachine machine);
    void cancel(CoffeeVendingMachine machine);
}

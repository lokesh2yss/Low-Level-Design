package coffee_vending_machine.state;

import coffee_vending_machine.CoffeeVendingMachine;
import coffee_vending_machine.entities.Coffee;

public class OutOfIngredientState implements VendingMachineState{
    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        System.out.println("Sorry, we are sold out.");
    }

    @Override
    public void insertMoney(CoffeeVendingMachine m, int a) {
        System.out.println("Sorry, we are sold out. Money refunded.");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine m) {
        System.out.println("Sorry, we are sold out.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        System.out.println("Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setState(new ReadyState());
    }
}

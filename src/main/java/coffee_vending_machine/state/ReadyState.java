package coffee_vending_machine.state;

import coffee_vending_machine.CoffeeVendingMachine;
import coffee_vending_machine.entities.Coffee;

public class ReadyState implements VendingMachineState{
    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        machine.setSelectedCoffee(coffee);
        machine.setState(new SelectingState());
        System.out.println(coffee.getCoffeeType() + " selected. Price: " + coffee.getPrice());
    }

    @Override
    public void insertMoney(CoffeeVendingMachine machine, int money) {
        System.out.println("Please select a coffee first.");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine machine) {
        System.out.println("Please select and pay first.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        System.out.println("Nothing to cancel.");
    }
}

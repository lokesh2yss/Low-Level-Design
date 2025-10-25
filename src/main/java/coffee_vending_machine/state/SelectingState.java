package coffee_vending_machine.state;

import coffee_vending_machine.CoffeeVendingMachine;
import coffee_vending_machine.entities.Coffee;

public class SelectingState implements VendingMachineState{
    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        System.out.println("Already selected. Please pay or cancel.");
    }

    @Override
    public void insertMoney(CoffeeVendingMachine machine, int amount) {
        machine.setMoneyInserted(machine.getMoneyInserted() + amount);
        System.out.println("Inserted " + amount + ". Total: " + machine.getMoneyInserted());
        if (machine.getMoneyInserted() >= machine.getSelectedCoffee().getPrice()) {
            machine.setState(new PaidState());
        }
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine machine) {
        System.out.println("Please insert enough money first.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        System.out.println("Transaction cancelled. Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setState(new ReadyState());
    }
}

package coffee_vending_machine;

import coffee_vending_machine.entities.CaramelSyrupDecorator;
import coffee_vending_machine.entities.Coffee;
import coffee_vending_machine.entities.ExtraSugarDecorator;
import coffee_vending_machine.enums.CoffeeType;
import coffee_vending_machine.enums.ToppingType;
import coffee_vending_machine.factory.CoffeeFactory;
import coffee_vending_machine.state.ReadyState;
import coffee_vending_machine.state.VendingMachineState;

import java.util.List;

public class CoffeeVendingMachine {
    private static final CoffeeVendingMachine instance = new CoffeeVendingMachine();
    private VendingMachineState state;
    private Coffee selectedCoffee;
    private int moneyInserted;
    private CoffeeVendingMachine() {
        this.state = new ReadyState();
        this.moneyInserted = 0;
    }
    public static CoffeeVendingMachine getInstance() {
        return instance;
    }
    public void selectCoffee(CoffeeType coffeeType, List<ToppingType> toppings) {
        // 1. Create the base coffee using the factory
        Coffee coffee = CoffeeFactory.createCoffee(coffeeType);
        // 2. Wrap it with decorators
        for(ToppingType toppingType: toppings) {
            coffee = switch (toppingType) {
                case EXTRA_SUGAR -> new ExtraSugarDecorator(coffee);
                case CARAMEL_SYRUP -> new CaramelSyrupDecorator(coffee);
            };
        }
        //3. Let the state handle the rest
        this.state.selectCoffee(this, coffee);
    }
    public void insertMoney(int amount) {
        this.state.insertMoney(this, amount);
    }
    public void dispenseCoffee() {
        this.state.dispenseCoffee(this);
    }
    public void cancel() {
        state.cancel(this);
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public VendingMachineState getState() {
        return state;
    }

    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }

    public void setSelectedCoffee(Coffee selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }

    public int getMoneyInserted() {
        return moneyInserted;
    }

    public void setMoneyInserted(int moneyInserted) {
        this.moneyInserted = moneyInserted;
    }
    public void reset() {
        this.selectedCoffee = null;
        this.moneyInserted = 0;
    }
}

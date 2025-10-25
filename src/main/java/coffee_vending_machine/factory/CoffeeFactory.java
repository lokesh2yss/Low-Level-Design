package coffee_vending_machine.factory;

import coffee_vending_machine.entities.Cappuccino;
import coffee_vending_machine.entities.Coffee;
import coffee_vending_machine.entities.Espresso;
import coffee_vending_machine.entities.Latte;
import coffee_vending_machine.enums.CoffeeType;

public class CoffeeFactory {
    public static Coffee createCoffee(CoffeeType type) {
        return switch (type) {
            case ESPRESSO -> new Espresso();
            case CAPPUCCINO -> new Cappuccino();
            case LATTE -> new Latte();
            default -> throw new IllegalArgumentException("Unsupported coffee type");
        };
    }
}

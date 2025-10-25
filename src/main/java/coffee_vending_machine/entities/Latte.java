package coffee_vending_machine.entities;

import coffee_vending_machine.enums.Ingredient;

import java.util.Map;

public class Latte extends Coffee{
    public Latte() {
        this.coffeeType = "Latte";
    }
    @Override
    protected void addCondiments() {
        System.out.println("- Adding steamed milk.");
    }

    @Override
    public int getPrice() {
        return 220;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.COFFEE_BEANS, 7, Ingredient.WATER, 30, Ingredient.MILK, 150);
    }
}

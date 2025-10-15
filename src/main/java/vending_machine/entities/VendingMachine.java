package vending_machine.entities;

import search_engine.entities.SearchResult;
import vending_machine.enums.Coin;
import vending_machine.state.IdleState;
import vending_machine.state.VendingMachineState;

public class VendingMachine {
    private static final VendingMachine instance = new VendingMachine();
    private final Inventory inventory = new Inventory();
    private String selectedItemCode;
    private VendingMachineState currentState;
    private int balance;
    private VendingMachine() {
        this.currentState = new IdleState(this);
    }
    public static VendingMachine getInstance() {
        return instance;
    }
    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }
    public Item addItem(String code, String name, int price, int quantity) {
        Item item = new Item(code, name, price);
        inventory.addItem(code, item, quantity);
        return item;
    }
    public void selectItem(String code) {
        currentState.selectItem(code);
    }
    public void dispense() {
        currentState.dispense();
    }
    public void dispenseItem() {
        Item item = inventory.getItem(selectedItemCode);
        if(balance >= item.getPrice()) {
            inventory.reduceStock(selectedItemCode);
            balance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());
            if (balance > 0) {
                System.out.println("Returning change: " + balance);
            }
        }
        reset();
        setState(new IdleState(this));
    }
    public void setSelectedItemCode(String code) {
        this.selectedItemCode = code;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addBalance(int value) {
        balance += value;
    }

    public Item getSelectedItem() {
        return inventory.getItem(selectedItemCode);
    }

    public int getBalance() {
        return balance;
    }

    public void reset() {
        selectedItemCode = null;
        balance = 0;
    }

    public void refundBalance() {
        System.out.println("Refunding: " + balance);
        balance = 0;
    }
}

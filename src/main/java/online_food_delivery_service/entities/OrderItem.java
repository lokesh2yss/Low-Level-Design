package online_food_delivery_service.entities;

public class OrderItem {
    private final MenuItem item;
    private final int quantity;
    private double subTotal;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.subTotal = quantity*item.getPrice();
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}

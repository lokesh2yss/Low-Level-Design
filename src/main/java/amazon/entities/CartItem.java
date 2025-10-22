package amazon.entities;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    public void incrementQuantity(int moreQuantity) {
        if(moreQuantity < 0) {
            throw new IllegalArgumentException("Negative quantity");
        }
        this.quantity += moreQuantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return quantity*product.getPrice();
    }
}

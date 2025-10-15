package inventory_management_system.entities;

public class Product {
    private final String productId;
    private final String name;
    private final String description;

    private Product(ProductBuilder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.description = builder.description;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Product{id='" + productId + "', name='" + name + "'}";
    }
    public String getDescription() {
        return description;
    }
    public static class ProductBuilder {
        private final String productId;
        private String name;
        private String description;
        public ProductBuilder(String productId) {
            this.productId = productId;
        }
        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
        public Product build() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Product name cannot be null or empty.");
            }
            return new Product(this);
        }
    }
}

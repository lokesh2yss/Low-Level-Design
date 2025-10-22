package amazon.entities;

import amazon.enums.ProductCategory;

import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private final String description;
    private double price;
    private final ProductCategory category;
    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.category = builder.category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private double price;
        private ProductCategory category;
        public Builder(String name, double price) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.price = price;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder category(ProductCategory category) {
            this.category = category;
            return this;
        }
        public Product build() {
            return new Product(this);
        }
    }
}

package inventory_management_system.factory;

import inventory_management_system.entities.Product;

public class ProductFactory {
    public static Product createProduct(String productId, String name, String description) {
        return new Product.ProductBuilder(productId)
                .withName(name)
                .withDescription(description)
                .build();
    }
}

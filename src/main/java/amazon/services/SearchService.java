package amazon.services;

import amazon.entities.Product;
import amazon.enums.ProductCategory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private final Collection<Product> productsCatalog;

    public SearchService(Collection<Product> catalog) {
        this.productsCatalog = catalog;
    }
    public List<Product> searchByName(String name) {
        return productsCatalog.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Product> searchByCategory(ProductCategory category) {
        return productsCatalog.stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }
}

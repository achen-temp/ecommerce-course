package com.ecommerce.product.repository;

import com.ecommerce.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static Set<Product> inventory;

    static {
        inventory = new HashSet<>();
        inventory.add(new Product("Book", "Java", 12.00, 50));
        inventory.add(new Product("Book", "JavaScript", 15.00, 10));
        inventory.add(new Product("Book", "Angular", 10.00, 20));
        inventory.add(new Product("Food", "Sandwich", 5.00, 30));
        inventory.add(new Product("Food", "Pizza", 6.99, 20));
        inventory.add(new Product("Food", "Apple", 1.99, 100));
        inventory.add(new Product("Movie", "Godzilla", 8.00, 30));
        inventory.add(new Product("Movie", "Avengers", 7.00, 20));
        inventory.add(new Product("Movie", "Batman", 10.00, 10));
    }

    public Set<Product> getAllProducts() {
        return inventory;
    }

    public Product getProductByName(String productName) {
        return inventory.stream().filter(
                product -> productName.equalsIgnoreCase(product.getProductName())
        ).findFirst().orElse(null);
    }

    public Set<Product> getProductByCategory(String category) {
        return inventory.stream().filter(
                product -> category.equalsIgnoreCase(product.getCategory())
        ).collect(Collectors.toSet());
    }
}

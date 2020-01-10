package com.ecommerce.product.service;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
public class ProductService {

    ProductRepository repository;

    public Set<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    public ProductRepository getRepository(){
        return repository;
    }

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean checkAvailability(String productName, int amount) {
        Product product = repository.getProductByName(productName);
        if (product == null || product.getProductAmount() < amount) {
            return false;
        }
        return true;
    }

    public double getPriceByProduct(String productName) {
        Product product = repository.getProductByName(productName);
        if(product != null) {
            return product.getProductUnitPrice();
        }
        return -1;
    }


    public Set<Product> getProductByCategory(String category) {
        return repository.getProductByCategory(category);
    }

    public String recommendByCategory(String category) {
        Set<Product> products = getProductByCategory(category);
        Random random = new Random();
        int temp = random.nextInt(products.size());

        int i = 0;
        for (Product p: products) {
            if (i == temp) {
                return p.toString();
            }
            i++;
        }
        return "";
    }
}

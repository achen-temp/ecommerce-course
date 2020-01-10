package com.ecommerce.product.controller;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping({"/", "/all_products"})
    public Set<Product> listAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/price/{name}/{amount}")
    public double getPriceByName(@PathVariable("name") String productName,
                                 @PathVariable("amount") int amount) throws Exception{
        boolean available = service.checkAvailability(productName, amount);
        if(available) {
            return service.getPriceByProduct(productName);
        }
        return -1;
    }

     @GetMapping("/products/category/{category}")
    public Set<Product> getProductByCategory(@PathVariable String category){
        return service.getProductByCategory(category);
    }

    @GetMapping("/products/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        Set<Product> products = listAllProducts();
        return products.stream().filter(
                product -> product.getProductName().equalsIgnoreCase(name)
        ).findAny().orElse(null);
    }

    @GetMapping("/product/recommend/{category}")
    public String recommendProductByCategory(@PathVariable String category){
        return service.recommendByCategory(category);
    }
}

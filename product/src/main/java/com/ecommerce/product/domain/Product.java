package com.ecommerce.product.domain;

import java.util.Objects;

public class Product {

    private String category;
    private String productName;
    private double productUnitPrice;
    private int productAmount;

    public Product(){
    }

    public Product(String category, String productName, double productUnitPrice, int productAmount) {
        this.category = category;
        this.productName = productName;
        this.productUnitPrice = productUnitPrice;
        this.productAmount = productAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getProductName(), product.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getProductName());
    }

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", productName='" + productName + '\'' +
                ", productUnitPrice=" + productUnitPrice +
                ", productAmount=" + productAmount +
                '}';
    }
}

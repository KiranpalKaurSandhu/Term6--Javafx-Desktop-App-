package com.example.term6project;

import java.util.List;

public class Product {
    private int productId;
    private String prodName;
    private List<ProductSupplier> productSuppliers; //Represents the Relationship with suppliers

    public Product(int productId, String prodName) {
        this.productId = productId;
        this.prodName = prodName;
    }
    // Getter and setter for productSuppliers
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    // Getter and setter for productSuppliers
    public List<ProductSupplier> getProductSuppliers() {
        return productSuppliers;
    }

    public void setProductSuppliers(List<ProductSupplier> productSuppliers) {
        this.productSuppliers = productSuppliers;
    }

    // Override toString method for displaying product name in UI components
    @Override
    public String toString() {
        return  prodName;
    }
}

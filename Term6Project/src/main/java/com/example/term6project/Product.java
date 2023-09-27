package com.example.term6project;

import java.util.List;

public class Product {
    private int ProductId;
    private String ProdName;
    private List<ProductSupplier> productSuppliers; //Represents the Relationship with suppliers

    public Product(int productId, String prodName) {
        this.ProductId = productId;
        this.ProdName = prodName;
    }
    // Getter and setter for productSuppliers
    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        this.ProductId = productId;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        this.ProdName = prodName;
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
        return  ProdName;
    }
}

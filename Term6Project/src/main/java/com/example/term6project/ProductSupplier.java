package com.example.term6project;

public class ProductSupplier {
    private int productSupplierId;
    private String productName; // String to store the product name
    private String supplierName; // String to store the supplier name

    public ProductSupplier(int productSupplierId, String productName, String supplierName) {
        this.productSupplierId = productSupplierId;
        this.productName = productName;
        this.supplierName = supplierName;
    }

    // Getters and setters for productSupplierId, productName, and supplierName
    public int getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

}

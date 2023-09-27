package com.example.term6project;

public class ProductSupplier {
    private int productSupplierId;
    private Product product; // Reference to the associated product
    private Supplier supplier; // Reference to the associated supplier

    public ProductSupplier(int productSupplierId, Product product, Supplier supplier) {
        this.productSupplierId = productSupplierId;
        this.product = product;
        this.supplier = supplier;
    }

    // Getters and setters for productSupplierId, product, and supplier
    public int getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}

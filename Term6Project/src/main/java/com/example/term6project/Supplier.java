package com.example.term6project;

import java.util.List;

public class Supplier {
    private int supplierId;
    private String supName;
    private List<ProductSupplier> productSuppliers;

    public Supplier(int supplierId, String supName) {
        this.supplierId = supplierId;
        this.supName = supName;
    }
    // Getters and setters for supplierId and supName

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }
    public List<ProductSupplier> getProductSuppliers() {

        return productSuppliers;
    }

    public void setProductSuppliers(List<ProductSupplier> productSuppliers) {
        this.productSuppliers = productSuppliers;
    }

    // Override toString method for displaying supplier name in UI components
    @Override
    public String toString() {
        return supName;
    }


}

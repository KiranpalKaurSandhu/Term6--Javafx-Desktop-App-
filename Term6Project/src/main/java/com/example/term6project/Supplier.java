package com.example.term6project;

public class Supplier {
    private int supplierId;
    private String supName;

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

    // Override toString method for displaying supplier name in UI components
    @Override
    public String toString() {
        return supName;
    }
}

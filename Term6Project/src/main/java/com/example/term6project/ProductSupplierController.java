package com.example.term6project;

/**
 * Sample Skeleton for 'AddProductSupplier-view.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ProductSupplierController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdd"
    private Button btnAdd; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProducts"
    private ComboBox<Product> cmbProducts; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSuppliers"
    private ComboBox<Supplier> cmbSuppliers; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert cmbProducts != null : "fx:id=\"cmbProducts\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert cmbSuppliers != null : "fx:id=\"cmbSuppliers\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";


    }

}


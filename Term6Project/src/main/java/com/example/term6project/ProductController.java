/**
 * Sample Skeleton for 'AddProduct-view.fxml' Controller Class
 */

package com.example.term6project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProductController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="tfProductId"
    private TextField tfProductId; // Value injected by FXMLLoader

    @FXML // fx:id="tfProductName"
    private TextField tfProductName; // Value injected by FXMLLoader

    private DashboardController mainController;

    // Set the main controller
    public void setMainController(DashboardController mainController) {
        this.mainController = mainController;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddProduct-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddProduct-view.fxml'.";
        assert tfProductId != null : "fx:id=\"tfProductId\" was not injected: check your FXML file 'AddProduct-view.fxml'.";
        assert tfProductName != null : "fx:id=\"tfProductName\" was not injected: check your FXML file 'AddProduct-view.fxml'.";


    }

    private void closeDialog() {
        btnCancel.getScene().getWindow().hide();
    }

}

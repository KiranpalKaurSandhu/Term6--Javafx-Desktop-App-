/**
 * Sample Skeleton for 'Dashboard-view.fxml' Controller Class
 */

package com.example.term6project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCustomer"
    private Button btnCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomer1"
    private Button btnCustomer1; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomer2"
    private Button btnCustomer2; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomer3"
    private Button btnCustomer3; // Value injected by FXMLLoader

    @FXML
    void handleBoookingButtonClick(ActionEvent event) {

    }

    @FXML
    void handleCustomerButtonClick(ActionEvent event) {

    }

    @FXML
    void handlePackageButtonClick(ActionEvent event) {

    }

    @FXML
    void handleProductButtonClick(ActionEvent event) {
        try {
            // Create an instance of the ProductController
            ProductController productController = new ProductController();

            // Load the Product view (FXML) and associate it with the ProductController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product-view.fxml"));
            loader.setController(productController); // Set the controller

            // Load the Product view
            Parent productView = loader.load();

            // Create a new scene with the Product view
            Scene productScene = new Scene(productView);

            // Get the current stage (window)
            Stage currentStage = (Stage) btnCustomer.getScene().getWindow();

            // Set the scene to the stage
            currentStage.setScene(productScene);

            // Show the stage
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCustomer != null : "fx:id=\"btnCustomer\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnCustomer1 != null : "fx:id=\"btnCustomer1\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnCustomer2 != null : "fx:id=\"btnCustomer2\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnCustomer3 != null : "fx:id=\"btnCustomer3\" was not injected: check your FXML file 'Dashboard-view.fxml'.";

    }

}

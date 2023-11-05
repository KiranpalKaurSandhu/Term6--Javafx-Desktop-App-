/**
 * Sample Skeleton for 'AddProduct-view.fxml' Controller Class
 */

package com.example.term6project;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/*
    Author : Kiranpal Kaur
    Description : This is the controller class for the "Add Product" dialog in a JavaFX application.
    It allows users to add or edit product information.
 */

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

    private String mode;


    private DashboardController mainController;

    public ProductController() {
        // Default constructor required for JavaFX controller initialization
    }


    // Set the main controller
    public void setMainController(DashboardController mainController)
    {

        this.mainController = mainController;
    }
    public void passModeToDialog(String mode) {
        this.mode = mode;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddProduct-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddProduct-view.fxml'.";
        assert tfProductId != null : "fx:id=\"tfProductId\" was not injected: check your FXML file 'AddProduct-view.fxml'.";
        assert tfProductName != null : "fx:id=\"tfProductName\" was not injected: check your FXML file 'AddProduct-view.fxml'.";

    btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            closeDialog();
        }
    });

    btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            btnSaveClicked(mouseEvent);
        }
    });
    }
    // Load database connection properties from a properties file
    private Properties getProperties() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\PC1\\Documents\\connection.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Handle the save button click event
    private void btnSaveClicked(MouseEvent mouseEvent) {
        if (isNotNull(tfProductName.getText())) {
            Properties p = getProperties();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection((String) p.get("url"), p);
                String sql = "";
                if (mode.equals("edit")) {
                    sql = "UPDATE `products` SET `ProdName`=? WHERE ProductId =?";
                    ;

                } else {
                    sql = "INSERT INTO `products`( `ProdName`) VALUES (?)";
                }
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, tfProductName.getText());

                if (mode.equals("edit")) {
                    stmt.setInt(2, Integer.parseInt(tfProductId.getText()));
                }
                int numRows = stmt.executeUpdate();
                if (numRows == 0) {
                    System.out.println("Save Failed.");
                } else {
                    System.out.println("Data saved Successfully.");
                }
                conn.close();
                // Close the dialog window when the save operation is successful
                closeDialog();

                // Refresh the product list in the dashboard
                mainController.refreshProductList();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Load product data for editing
    public void loadData(Product t1) {
        tfProductId.setText(t1.getProductId() + "");
        tfProductName.setText(t1.getProdName());
    }

    // Close the dialog window
    private void closeDialog() {
        if (mainController != null) {
            mainController.clearTableSelections();
        }
        btnCancel.getScene().getWindow().hide();
    }

    /*Following filter and message boxes authored by Greg Bevington*/
    private boolean isNotNull(String string){
        if(string.isEmpty()){
            failureNotification();
            return false;
        }
        else{
            successNotification();
            return true;
        }
    }

    private void failureNotification(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Product failed to save.");
        alert.setHeaderText("Product failed to save. Name field must not be blank.");
        //button label
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        //user response
        alert.showAndWait().ifPresent(response ->{
            if (response == okButton){
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.close();
            }
        });
    }
    private void successNotification(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Changes saved.");
        alert.setHeaderText("Product saved successfully.");
        //button label
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        //user response
        alert.showAndWait().ifPresent(response ->{
            if (response == okButton){
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.close();
            }
        });
    }

}

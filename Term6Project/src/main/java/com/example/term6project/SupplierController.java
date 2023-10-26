/**
 * Sample Skeleton for 'AddSupplier-view.fxml' Controller Class
 */

package com.example.term6project;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SupplierController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupplierId"
    private TextField tfSupplierId; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupplierName"
    private TextField tfSupplierName; // Value injected by FXMLLoader
    private String mode;

    private DashboardController mainController;

    public SupplierController() {
        // Default constructor required for JavaFX controller initialization
    }

    // Set the main controller
    public void setMainController(DashboardController mainController) {

        this.mainController = mainController;
    }
    public void passModeToDialog(String mode) {
        this.mode = mode;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddSupplier-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddSupplier-view.fxml'.";
        assert tfSupplierId != null : "fx:id=\"tfSupplierId\" was not injected: check your FXML file 'AddSupplier-view.fxml'.";
        assert tfSupplierName != null : "fx:id=\"tfSupplierName\" was not injected: check your FXML file 'AddSupplier-view.fxml'.";

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

    private Properties getProperties() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Alisa\\Documents\\connection.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void btnSaveClicked(MouseEvent mouseEvent) {
        Properties p = getProperties();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection((String) p.get("url"), p);
            String sql = "";
            PreparedStatement stmt = null;

            if (mode.equals("edit")) {
                // For editing, update only the SupName
                sql = "UPDATE `suppliers` SET `SupName`=? WHERE SupplierId = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, tfSupplierName.getText());
                stmt.setInt(2, Integer.parseInt(tfSupplierId.getText()));

                // Disable tfSupplierId during editing
                tfSupplierId.setDisable(true);
            } else {
                // For inserting, include SupplierId (user input)
                sql = "INSERT INTO `suppliers`( `SupplierId`, `SupName`) VALUES ( ?,?)";
                stmt = conn.prepareStatement(sql);
                // Query the maximum supplier ID and increment it by 1
                int maxId = getMaxSupplierId(conn);
                int newId = maxId + 1;

                stmt.setInt(1, newId);
                stmt.setString(2, tfSupplierName.getText());
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
            mainController.refreshSupplierList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to get the maximum supplier ID from the table
    private int getMaxSupplierId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(SupplierId) FROM suppliers";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0; // If there are no suppliers in the table yet
        }
    }

    public void loadData(Supplier t1) {
        tfSupplierId.setText(t1.getSupplierId() + "");
        tfSupplierName.setText(t1.getSupName());
    }

    private void closeDialog() {
        if (mainController != null) {
            mainController.clearTableSelections();
        }
        btnCancel.getScene().getWindow().hide();
    }

}


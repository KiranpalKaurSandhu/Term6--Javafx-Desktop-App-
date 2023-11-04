/**
 * Sample Skeleton for 'AddProductSupplier-view.fxml' Controller Class
 */

package com.example.term6project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/*
Author : Kiranpal Kaur
Description : This is the controller class for managing the relationship between products and suppliers
 in a JavaFX application. It allows users to associate products with suppliers and displays the existing
  relationships.
*/
public class ProductSupplierController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProducts"
    private ComboBox<Product> cmbProducts; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSuppliers"
    private ComboBox<Supplier> cmbSuppliers; // Value injected by FXMLLoader

    @FXML // fx:id="colProductId"
    private TableColumn<ProductSupplier, Product> colProductId; // Value injected by FXMLLoader

    @FXML // fx:id="colProductSupplierId"
    private TableColumn<ProductSupplier, Integer> colProductSupplierId; // Value injected by FXMLLoader

    @FXML // fx:id="colSupplierId"
    private TableColumn<ProductSupplier, Supplier> colSupplierId; // Value injected by FXMLLoader

    @FXML // fx:id="tvProductSupplier"
    private TableView<ProductSupplier> tvProductSupplier; // Value injected by FXMLLoader
    String mode = "add";
    ;

    private ObservableList<Product> productsList = FXCollections.observableArrayList();
    private ObservableList<Supplier> suppliersList = FXCollections.observableArrayList();
    private ObservableList<ProductSupplier> productSupplierData = FXCollections.observableArrayList();


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert cmbProducts != null : "fx:id=\"cmbProducts\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert cmbSuppliers != null : "fx:id=\"cmbSuppliers\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert colProductId != null : "fx:id=\"colProductId\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert colProductSupplierId != null : "fx:id=\"colProductSupplierId\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert colSupplierId != null : "fx:id=\"colSupplierId\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";
        assert tvProductSupplier != null : "fx:id=\"tvProductSupplier\" was not injected: check your FXML file 'AddProductSupplier-view.fxml'.";


        populateProductsSupplierTextView();

        // Populate ComboBoxes with all product and supplier names
        getProducts();
        getSuppliers();


        cmbProducts.setItems(productsList);
        cmbSuppliers.setItems(suppliersList);


        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Product selectedProduct = cmbProducts.getValue();
                Supplier selectedSupplier = cmbSuppliers.getValue();

                if (selectedProduct != null && selectedSupplier != null) {
                    saveProductSupplier(selectedProduct, selectedSupplier);

                    // Clear the ComboBox selections
                    cmbProducts.getSelectionModel().clearSelection();
                    cmbSuppliers.getSelectionModel().clearSelection();
                    // Refresh the TableView with updated data
                    getProductsSupplier();

                }
            }

        });

    }

    // Load the list of available products from the database.
    private void getProducts() {
        cmbProducts.getItems().clear();
        productsList.clear();

        String url = "";
        String user = "";
        String password = "";

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Kiran\\Documents\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");

            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("prodName");

                // Populate ComboBox with all product names
                cmbProducts.getItems().add(new Product(productId, productName));
                productsList.add(new Product(productId, productName)); // Add to the observable list
            }
            conn.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Load the list of available suppliers from the database.
    private void getSuppliers() {
        cmbSuppliers.getItems().clear();
        suppliersList.clear();

        String url = "";
        String user = "";
        String password = "";

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Kiran\\Documents\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Suppliers");

            while (rs.next()) {
                int supplierId = rs.getInt("supplierId");
                String supplierName = rs.getString("supName");

                // Populate ComboBox with all supplier names
                cmbSuppliers.getItems().add(new Supplier(supplierId, supplierName));
                suppliersList.add(new Supplier(supplierId, supplierName)); // Add to the observable list
            }
            conn.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Save the product-supplier relationship to the database
    private void saveProductSupplier(Product selectedProduct, Supplier selectedSupplier) {
        Properties p = getProperties();
        Connection conn = null;

        try {
            conn = DriverManager.getConnection((String) p.get("url"), p);
            String sql = "";

            if (mode.equals("edit")) {
                // For editing, you should use UPDATE statement
                sql = "UPDATE `products_suppliers` SET `ProductId`=?, `SupplierId`=? WHERE `ProductSupplierId`=?";
            } else {
                // For adding, use INSERT statement without specifying ProductSupplierId
                sql = "INSERT INTO `products_suppliers`(`ProductId`, `SupplierId`) VALUES (?,?)";
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                if (mode.equals("edit")) {
                    // In edit mode, you should set the values and ProductSupplierId
                    preparedStatement.setInt(1, selectedProduct.getProductId());
                    preparedStatement.setInt(2, selectedSupplier.getSupplierId());
                } else {
                    // In add mode, you only need to set the ProductId and SupplierId
                    preparedStatement.setInt(1, selectedProduct.getProductId());
                    preparedStatement.setInt(2, selectedSupplier.getSupplierId());
                }

                // Execute the SQL statement
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the connection in a final block
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Handle the exception or log it
                    throw new RuntimeException(e);
                }
            }
        }
    }


    // Retrieve database connection properties from a file
    private Properties getProperties() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Kiran\\Documents\\connection.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Load and display the list of product-supplier relationships from the database.
    private void getProductsSupplier() {
        productSupplierData.clear();
        cmbProducts.getItems().clear();
        cmbSuppliers.getItems().clear();

        String url = "";
        String user = "";
        String password = "";

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Kiran\\Documents\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ps.ProductSupplierId, p.prodName, s.supName " +
                    "FROM Products_Suppliers ps " +
                    "JOIN Products p ON ps.ProductId = p.productId " +
                    "JOIN Suppliers s ON ps.SupplierId = s.supplierId");

            while (rs.next()) {
                int productSupplierId = rs.getInt("ProductSupplierId");
                String productName = rs.getString("prodName");
                String supplierName = rs.getString("supName");

                productSupplierData.add(new ProductSupplier(productSupplierId, productName, supplierName));
                // Populate ComboBoxes directly
                cmbProducts.getItems().add(new Product(productSupplierId, productName));
                cmbSuppliers.getItems().add(new Supplier(productSupplierId, supplierName));
            }
            conn.close();


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Handle the Save button click event.
    @FXML
    private void btnSaveClicked() {
        if (showConfirmationDialog("Save", "Are you sure you want to save these changes?")) {
            Product selectedProduct = cmbProducts.getValue();
            Supplier selectedSupplier = cmbSuppliers.getValue();
            saveProductSupplier(selectedProduct, selectedSupplier);

            // Refresh the product list in the dashboard


            // Update the PropertyValueFactory for colProductSupplierId
            populateProductsSupplierTextView();
            successNotification();
        }
    }

    // Handle the Cancel button click event
    @FXML
    private void btnCancelClicked() {    //close add/edit window
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

        //!!! add confirmation dialog
    }

    // Show a confirmation dialog with the given title and content.
    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(okButton, cancelButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        alert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                //!!!!
            }
        });
        return (alert.getResult() == okButton);
    }

  public void populateProductsSupplierTextView(){
    // Update the PropertyValueFactory for colProductSupplierId
        colProductSupplierId.setCellValueFactory(new PropertyValueFactory<>("productSupplierId"));
    // Update the PropertyValueFactory for colProductId and colSupplierId
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        tvProductSupplier.setItems(productSupplierData);

    getProductsSupplier();

    }

    // Show a success notification to the user
    private void successNotification(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Changes saved.");
        alert.setHeaderText("Changes saved successfully.");
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






/**
 * Sample Skeleton for 'Product-view.fxml' Controller Class
 */

package com.example.term6project;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;


public class ProductController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="btnAddProduct"
    private Button btnAddProduct; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddSupplier"
    private Button btnAddSupplier; // Value injected by FXMLLoader

    @FXML // fx:id="productActionsColumn"
    private TableColumn<Product, Void> productActionsColumn; // Value injected by FXMLLoader

    @FXML // fx:id="productIdColumn"
    private TableColumn<Product, Integer> productIdColumn; // Value injected by FXMLLoader

    @FXML // fx:id="productNAmeColumn"
    private TableColumn<Product, String> productNameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="supplierActionsColumn"
    private TableColumn<Supplier, Void> supplierActionsColumn; // Value injected by FXMLLoader

    @FXML // fx:id="supplierIdColumn"
    private TableColumn<Supplier, Integer> supplierIdColumn; // Value injected by FXMLLoader

    @FXML // fx:id="supplierNameColumn
    private TableColumn<Supplier, String> supplierNameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="tvProducts"
    private TableView<Product> tvProducts; // Value injected by FXMLLoader

    @FXML // fx:id="tvSuppliers"
    private TableView<Supplier> tvSuppliers; // Value injected by FXMLLoader

    private String mode;

    ObservableList<Product> products = FXCollections.observableArrayList();
    ObservableList<Supplier> suppliers = FXCollections.observableArrayList();


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAddProduct != null : "fx:id=\"btnAddProduct\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert btnAddSupplier != null : "fx:id=\"btnAddSupplier\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert productActionsColumn != null : "fx:id=\"productActionsColumn\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert productIdColumn != null : "fx:id=\"productIdColumn\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert productNameColumn != null : "fx:id=\"productNAmeColumn\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert supplierActionsColumn != null : "fx:id=\"supplierActionsColumn\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert supplierIdColumn != null : "fx:id=\"supplierIdColumn\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert supplierNameColumn != null : "fx:id=\"supplierNameColumn\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert tvProducts != null : "fx:id=\"tvProducts\" was not injected: check your FXML file 'Product-view.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'Product-view.fxml'.";

        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("prodName"));
        // Set the cell factory for the "Actions" column in the product table
        productActionsColumn.setCellFactory(col -> new ActionsTableCell<Product>());

        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("supplierId"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supName"));
        // Set the cell factory for the "Actions" column in the supplier table
        supplierActionsColumn.setCellFactory(col -> new ActionsTableCell<Supplier>());


        tvProducts.setItems(products);
        tvSuppliers.setItems(suppliers);

        getProducts();
        getSuppliers();


        // Set the cell factory for the "Actions" column
        productActionsColumn.setCellFactory(new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(TableColumn<Product, Void> param) {
                return new ActionsTableCell<>();
            }
        });

        supplierActionsColumn.setCellFactory(new Callback<TableColumn<Supplier, Void>, TableCell<Supplier, Void>>() {
            @Override
            public TableCell<Supplier, Void> call(TableColumn<Supplier, Void> param) {
                return new ActionsTableCell<>();
            }
        });

        btnAddProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        btnAddSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }


    private void getSuppliers() {
        suppliers.clear();

        String url = "";
        String user = "";
        String password = "";

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\kiran\\Documents\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Suppliers");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt(1), rs.getString(2)));
            }
            conn.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void getProducts() {
        products.clear();

        String url = "";
        String user = "";
        String password = "";

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\kiran\\Documents\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Products");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2)));
            }
            conn.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

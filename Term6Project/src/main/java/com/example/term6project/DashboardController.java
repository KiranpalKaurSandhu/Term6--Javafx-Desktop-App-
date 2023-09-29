/**
 * Sample Skeleton for 'Dashboard-view.fxml' Controller Class
 */
/**
 * Sample Skeleton for 'Dashboard-view.fxml' Controller Class
 */

package com.example.term6project;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class DashboardController {
    /**
     * Sample Skeleton for 'Dashboard-view.fxml' Controller Class
     */


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdd"
    private Button btnAdd; // Value injected by FXMLLoader

    @FXML // fx:id="btnBookings"
    private Button btnBookings; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancel"
    private Label btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomers"
    private Button btnCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="btnPackages"
    private Button btnPackages; // Value injected by FXMLLoader

    @FXML // fx:id="btnProducts"
    private Button btnProducts; // Value injected by FXMLLoader

    @FXML // fx:id="btnSuppliers"
    private Button btnSuppliers; // Value injected by FXMLLoader

    @FXML // fx:id="pnCustomers"
    private GridPane pnCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="pnPackages"
    private GridPane pnPackages; // Value injected by FXMLLoader

    @FXML // fx:id="pnProducts"
    private GridPane pnProducts; // Value injected by FXMLLoader

    @FXML // fx:id="pnSuppliers"
    private GridPane pnSuppliers; // Value injected by FXMLLoader

    @FXML // fx:id="pnBookings"
    private GridPane pnBookings; // Value injected by FXMLLoader

    @FXML // fx:id="tvBookings"
    private TableView<Booking> tvBookings; // Value injected by FXMLLoader

    @FXML // fx:id="tvCustomers"
    private TableView<Customer> tvCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="tvPackages"
    private TableView<Packages> tvPackages; // Value injected by FXMLLoader

    @FXML // fx:id="tvProducts"
    private TableView<Product> tvProducts; // Value injected by FXMLLoader

    @FXML // fx:id="tvSuppliers"
    private TableView<Supplier> tvSuppliers; // Value injected by FXMLLoader

    @FXML // fx:id="colAgentId"
    private TableColumn<Customer, Integer> colAgentId; // Value injected by FXMLLoader




    @FXML // fx:id="colCustAddress"
    private TableColumn<Customer, String> colCustAddress; // Value injected by FXMLLoader

    @FXML // fx:id="colCustBusPhone"
    private TableColumn<Customer, String> colCustBusPhone; // Value injected by FXMLLoader
    @FXML // fx:id="colCustBusPhone"
    private TableColumn<Customer, String> colCustHomePhone; // Value injected by FXMLLoader

    @FXML // fx:id="colCustCity"
    private TableColumn<Customer, String> colCustCity; // Value injected by FXMLLoader

    @FXML // fx:id="colCustCountry"
    private TableColumn<Customer, String> colCustCountry; // Value injected by FXMLLoader

    @FXML // fx:id="colCustEmail"
    private TableColumn<Customer, String> colCustEmail; // Value injected by FXMLLoader

    @FXML // fx:id="colCustFirstName"
    private TableColumn<Customer, String> colCustFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="colCustLastName"
    private TableColumn<Customer, String> colCustLastName; // Value injected by FXMLLoader

    @FXML // fx:id="colCustPostal"
    private TableColumn<Customer, String> colCustPostal; // Value injected by FXMLLoader

    @FXML // fx:id="colCustProv"
    private TableColumn<Customer, String> colCustProv; // Value injected by FXMLLoader

    @FXML // fx:id="colCustomerId"
    private TableColumn<Customer, Integer> colCustomerCustomerId; // Value injected by FXMLLoader


    @FXML // fx:id="colPackageId"
    private TableColumn<Packages, Integer> colPackagePackageId; // Value injected by FXMLLoader
    @FXML // fx:id="colPackageId"
    private TableColumn<Booking, Integer> colBookingPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="colPkgDesc"
    private TableColumn<Packages, String> colPkgDesc; // Value injected by FXMLLoader

    @FXML // fx:id="colPkgAgenctCommission"
    private TableColumn<Packages, Double> colPkgAgencyCommission; // Value injected by FXMLLoader

    @FXML // fx:id="colPkgBasePrice"
    private TableColumn<Packages, Double> colPkgBasePrice; // Value injected by FXMLLoader

    @FXML // fx:id="colPkgEndDate"
    private TableColumn<Packages, Date> colPkgEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="colPkgName"
    private TableColumn<Packages, String> colPkgName; // Value injected by FXMLLoader

    @FXML // fx:id="colPkgStartDate"
    private TableColumn<Packages, Date> colPkgStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="colProdName"
    private TableColumn<Product, String> colProdName; // Value injected by FXMLLoader

    @FXML // fx:id="colProductId"
    private TableColumn<Product, Integer> colProductId; // Value injected by FXMLLoader

    @FXML // fx:id="colSupName"
    private TableColumn<Supplier, String> colSupName; // Value injected by FXMLLoader

    @FXML // fx:id="colSupplierId"
    private TableColumn<Supplier, Integer> colSupplierId; // Value injected by FXMLLoader

    @FXML // fx:id="colTravelerCount"
    private TableColumn<Booking, Double> colTravelerCount; // Value injected by FXMLLoader

    @FXML // fx:id="colTripTypeId"
    private TableColumn<Booking, String> colTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="colBookingDate"
    private TableColumn<Booking, Date> colBookingDate; // Value injected by FXMLLoader

    @FXML // fx:id="colBookingId"
    private TableColumn<Booking, Integer> colBookingId; // Value injected by FXMLLoader

    @FXML // fx:id="colBookingNo"
    private TableColumn<Booking, String> colBookingNo; // Value injected by FXMLLoader

    @FXML // fx:id="colCustomerId"
    private TableColumn<Booking, Integer> colBookingCustomerId; // Value injected by FXMLLoader




    @FXML
    void handleBtnPackages(ActionEvent event) {
        // Hide all GridPanes and show the Packages GridPane.
        hideAllGridPanes();
        pnPackages.setVisible(true);
    }
    @FXML
    void handleBtnProducts(ActionEvent event) {
        // Hide all GridPanes and show the Products GridPane.
        hideAllGridPanes();
        pnProducts.setVisible(true);
    }

    @FXML
    void handleBtnSuppliers(ActionEvent event) {
        // Hide all GridPanes and show the Suppliers GridPane.
        hideAllGridPanes();
        pnSuppliers.setVisible(true);
    }

    @FXML
    void handleBtnBookings(ActionEvent event) {
        // Hide all GridPanes and show the Bookings GridPane.
        hideAllGridPanes();
        pnBookings.setVisible(true);
    }

    @FXML
    void handleBtnCustomers(ActionEvent event) {
        // Hide all GridPanes and show the Customers GridPane.
        hideAllGridPanes();
        pnCustomers.setVisible(true);
    }

    private ObservableList<Booking> bookingData = FXCollections.observableArrayList();
    private ObservableList<Product> productData = FXCollections.observableArrayList();
    private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
    private ObservableList<Packages> packagesData = FXCollections.observableArrayList();
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnBookings != null : "fx:id=\"btnBookings\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnProducts != null : "fx:id=\"btnProducts\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnSuppliers != null : "fx:id=\"btnSuppliers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert pnBookings != null : "fx:id=\"pnBookings\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert pnCustomers != null : "fx:id=\"pnCustomers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert pnPackages != null : "fx:id=\"pnPackages\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert pnProducts != null : "fx:id=\"pnProducts\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert pnSuppliers != null : "fx:id=\"pnSuppliers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert tvBookings != null : "fx:id=\"tvBookings\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert tvCustomers != null : "fx:id=\"tvCustomers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert tvPackages != null : "fx:id=\"tvPackages\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert tvProducts != null : "fx:id=\"tvProducts\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";

        // Initialize your controller (e.g., hide all GridPanes except the default one).
        pnPackages.setVisible(true);
        pnProducts.setVisible(false);
        pnSuppliers.setVisible(false);
        pnBookings.setVisible(false);
        pnCustomers.setVisible(false);

        colBookingId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingId"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory<Booking, Date>("bookingDate"));
        colBookingNo.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingNo"));
        colTravelerCount.setCellValueFactory(new PropertyValueFactory<Booking, Double>("travelerCount"));
        colBookingCustomerId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("customerId"));
        colTripTypeId.setCellValueFactory(new PropertyValueFactory<Booking, String>("tripTypeId"));
        colBookingPackageId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("packageId"));

        tvBookings.setItems(bookingData);


        colProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        colProdName.setCellValueFactory(new PropertyValueFactory<Product, String>("prodName"));

        tvProducts.setItems(productData);

        colSupplierId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supName"));

        tvSuppliers.setItems(supplierData);

        colCustomerCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("custCity"));
        colCustProv.setCellValueFactory(new PropertyValueFactory<Customer, String>("custProv"));
        colCustPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPostal"));
        colCustCountry.setCellValueFactory(new PropertyValueFactory<Customer, String>("custCountry"));
        colCustHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custHomePhone"));
        colCustBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
        colAgentId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("agentId"));

        tvCustomers.setItems(customerData);

        colPackagePackageId.setCellValueFactory(new PropertyValueFactory<Packages, Integer>("packageId"));
        colPkgName.setCellValueFactory(new PropertyValueFactory<Packages, String>("pkgName"));
        colPkgStartDate.setCellValueFactory(new PropertyValueFactory<Packages, Date>("pkgStartDate"));
        colPkgEndDate.setCellValueFactory(new PropertyValueFactory<Packages, Date>("pkgEndDate"));
        colPkgDesc.setCellValueFactory(new PropertyValueFactory<Packages, String>("pkgDesc"));
        colPkgBasePrice.setCellValueFactory(new PropertyValueFactory<Packages, Double>("pkgBasePrice"));
        colPkgAgencyCommission.setCellValueFactory(new PropertyValueFactory<Packages, Double>("pkgAgencyCommission"));

        tvPackages.setItems(packagesData);

        // Call fetchTableData to populate data for each table
        fetchTableData("Bookings", bookingData);
        fetchTableData("Products", productData);
        fetchTableData("Packages", packagesData);
        fetchTableData("Customers", customerData);
        fetchTableData("Suppliers", supplierData);

    }


    // Helper method to hide all GridPanes.
    private void hideAllGridPanes() {
        pnPackages.setVisible(false);
        pnProducts.setVisible(false);
        pnSuppliers.setVisible(false);
        pnBookings.setVisible(false);
        pnCustomers.setVisible(false);
    }

   private void fetchTableData(String tableName, ObservableList<?> data) {
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
           ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

           while (rs.next()) {
               if ("Bookings".equals(tableName)) {
                   bookingData.add(new Booking(rs.getInt(1), rs.getDate(2), rs.getString(3),
                           rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
               } else if ("Products".equals(tableName)) {
                   productData.add(new Product(rs.getInt(1), rs.getString(2)));
               } else if ("Packages".equals(tableName)) {
                   packagesData.add(new Packages(rs.getInt(1), rs.getString(2), rs.getDate(3),
                           rs.getDate(4), rs.getString(5), rs.getDouble(6),
                           rs.getDouble(7)
                   ));
               } else if ("Customers".equals(tableName)) {
                   customerData.add(new Customer( rs.getInt(1), rs.getString(2), rs.getString(3),
                           rs.getString(4), rs.getString(5), rs.getString(6),
                           rs.getString(7), rs.getString(8), rs.getString(9),
                           rs.getString(10), rs.getString(11), rs.getInt(12)
                   ));
               } else if ("Suppliers".equals(tableName)) {
                   supplierData.add(new Supplier(rs.getInt(1), rs.getString(2)));
               }

           }
           conn.close();
       } catch (IOException | SQLException e) {
           throw new RuntimeException(e);
       }
   }
}


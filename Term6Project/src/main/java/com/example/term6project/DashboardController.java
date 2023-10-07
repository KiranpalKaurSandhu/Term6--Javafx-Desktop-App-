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

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardController {
    /**
     * Sample Skeleton for 'Dashboard-view.fxml' Controller Class
     */


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;



    @FXML // fx:id="btnAddBookings"
    private Button btnAddBookings; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddCustomers"
    private Button btnAddCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddPackages"
    private Button btnAddPackages; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddProducts"
    private Button btnAddProducts; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddSuppliers"
    private Button btnAddSuppliers; // Value injected by FXMLLoader

    @FXML // fx:id="btnBookings"
    private Button btnBookings; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomers"
    private Button btnCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="btnEditBookings"
    private Button btnEditBookings; // Value injected by FXMLLoader

    @FXML // fx:id="btnEditCustomers"
    private Button btnEditCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="btnEditPackages"
    private Button btnEditPackages; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddProductSupplier"
    private Button btnAddProductSupplier; // Value injected by FXMLLoader

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

    private String mode;

    @FXML
    void openAddProductSupplierView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProductSupplier-view.fxml"));
            Parent root = loader.load();
            ProductSupplierController controller = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Product Supplier Relationship");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


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
    @FXML
    void handleEditButton(ActionEvent event) {
        String tableId = ((Button) event.getSource()).getId();
        String dialogFXML = "";

        switch (tableId) {
            case "btnEditBookings":
                dialogFXML = "AddBooking-view.fxml";
                break;
            case "btnEditCustomers":
                dialogFXML = "AddCustomer-view.fxml";
                break;
            case "btnEditPackages":
                dialogFXML = "AddPackage-view.fxml";
                break;

        }
        if (!dialogFXML.isEmpty()) {
            openEditDialog(dialogFXML);
        }
    }
    @FXML
    void handleAddButton(ActionEvent event) {
        String tableId = ((Button) event.getSource()).getId();
        String dialogFXML = "";

        switch (tableId) {
            case "btnAddProducts":
                dialogFXML = "AddProduct-view.fxml";
                break;
            case "btnAddSuppliers":
                dialogFXML = "AddSupplier-view.fxml";
                break;
            case "btnAddBookings":
                dialogFXML = "AddBooking-view.fxml";
                break;
            case "btnAddCustomers":
                dialogFXML = "AddCustomer-view.fxml";
                break;
            case "btnAddPackages":
                dialogFXML = "AddPackage-view.fxml";
                break;
        }

        if (!dialogFXML.isEmpty()) {
            openAddDialog(dialogFXML);
        }
    }

    private void openAddDialog(String dialogFXML) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(dialogFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void openEditDialog(String dialogFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(dialogFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ObservableList<Booking> bookingData = FXCollections.observableArrayList();
    private ObservableList<Product> productData = FXCollections.observableArrayList();
    private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
    private ObservableList<Packages> packagesData = FXCollections.observableArrayList();
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        assert btnAddBookings != null : "fx:id=\"btnAddBookings\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnAddCustomers != null : "fx:id=\"btnAddCustomers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnAddPackages != null : "fx:id=\"btnAddPackages\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnAddProducts != null : "fx:id=\"btnAddProducts\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnAddSuppliers != null : "fx:id=\"btnAddSuppliers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnBookings != null : "fx:id=\"btnBookings\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnEditBookings != null : "fx:id=\"btnEditBookings\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnEditCustomers != null : "fx:id=\"btnEditCustomers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnEditPackages != null : "fx:id=\"btnEditPackages\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnProducts != null : "fx:id=\"btnProducts\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnSuppliers != null : "fx:id=\"btnSuppliers\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnAddProductSupplier != null : "fx:id=\"btnAddProductSupplier\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
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

        btnAddProducts.setOnAction(event -> openProductDialog("add", null));


        tvProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product selectedProduct) {
                if (tvProducts.getSelectionModel().isSelected(tvProducts.getSelectionModel().getSelectedIndex())) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            openProductDialog("edit", selectedProduct);
                        }
                    });
                }
            }
        });

        // Load Product Data
        fetchTableData("Products", productData);

        colSupplierId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supName"));

        tvSuppliers.setItems(supplierData);

        btnAddSuppliers.setOnAction(event -> openSupplierDialog("add", null));


        tvSuppliers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
            @Override
            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier selectedSupplier) {
                if (tvSuppliers.getSelectionModel().isSelected(tvSuppliers.getSelectionModel().getSelectedIndex())) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            openSupplierDialog("edit", selectedSupplier);
                        }
                    });
                }
            }
        });
        // Load Product Data
        fetchTableData("Suppliers", supplierData);

        btnAddProductSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openAddProductSupplierView();
            }
        });

        colCustomerCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
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

        tvPackages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Packages>() {  //!!! greg, testing
            @Override
            public void changed(ObservableValue<? extends Packages> observableValue, Packages packageItem, Packages selectedPackage) {
                if (tvPackages.getSelectionModel().isSelected(tvPackages.getSelectionModel().getSelectedIndex())) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            openPackageDialog("edit", selectedPackage);
                        }
                    });
                }
            }
        });
        //!!!

        // Call fetchTableData to populate data for each table
        fetchTableData("Bookings", bookingData);
        fetchTableData("Products", productData);
        fetchTableData("Packages", packagesData);
        fetchTableData("Customers", customerData);
        fetchTableData("Suppliers", supplierData);


    }

    private void openPackageDialog(String mode, Packages currentPackage) { //!!! greg, working on this - why doesn't it like 'package'?

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPackage-view.fxml"));
            Parent root = null;
            root = loader.load();

        TravelPackageController packageController = loader.getController();
        packageController.passModeToDialog(mode);
        packageController.setMainController(this);

        if (mode.equals("edit")) {
            packageController.populateEditForm(currentPackage);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(mode.equals("add") ? "Add Package" : "Edit Package");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openProductDialog(String mode, Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct-view.fxml"));
            Parent root = loader.load();
            ProductController productController = loader.getController();
            productController.passModeToDialog(mode);
            productController.setMainController(this);

            if (mode.equals("edit")) {
                productController.loadData(product);
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(mode.equals("add") ? "Add Product" : "Edit Product");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void openSupplierDialog(String mode, Supplier supplier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSupplier-view.fxml"));
            Parent root = loader.load();
            SupplierController supplierController = loader.getController();
            supplierController.passModeToDialog(mode);
            supplierController.setMainController(this);

            if (mode.equals("edit")) {
                supplierController.loadData(supplier);
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(mode.equals("add") ? "Add Supplier" : "Edit Supplier");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
           FileInputStream fis = new FileInputStream("C:\\Users\\PC1\\Documents\\connection.properties");
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


    public void refreshProductList() {
        productData.clear();
        fetchTableData("Products", productData);
    }

    public void refreshSupplierList() {
        supplierData.clear();
        fetchTableData("Suppliers", supplierData);
    }

    public void clearTableSelections() {
        if (tvSuppliers != null && !tvSuppliers.getSelectionModel().isEmpty()) {
            tvSuppliers.getSelectionModel().clearSelection();
        }

        if (tvProducts != null && !tvProducts.getSelectionModel().isEmpty()) {
            tvProducts.getSelectionModel().clearSelection();
        }
    }
}


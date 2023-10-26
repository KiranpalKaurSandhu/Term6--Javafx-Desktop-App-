package com.example.term6project;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class BookingController {
        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="btnCancel"
        private Button btnCancel; // Value injected by FXMLLoader

        @FXML // fx:id="btnSave"
        private Button btnSave; // Value injected by FXMLLoader

        @FXML // fx:id="cbPackageId"
        private ComboBox<Packages> cbPackageId; // Value injected by FXMLLoader

        @FXML // fx:id="cbTripType"
        private ComboBox<TripType> cbTripType; // Value injected by FXMLLoader

        @FXML // fx:id="tfBookingDate"
        private TextField tfBookingDate; // Value injected by FXMLLoader

        @FXML // fx:id="tfBookingId"
        private TextField tfBookingId; // Value injected by FXMLLoader

        @FXML // fx:id="tfBookingNo"
        private TextField tfBookingNo; // Value injected by FXMLLoader

        @FXML // fx:id="cbCustomerId"
        private ComboBox<Customer> cbCustomerId; // Value injected by FXMLLoader

        @FXML // fx:id="tfTravelerCount"
        private TextField tfTravelerCount; // Value injected by FXMLLoader

        private String mode;

    private DashboardController mainController;

    ObservableList customers = FXCollections.observableArrayList();
    ObservableList tripTypes = FXCollections.observableArrayList();
    ObservableList packages = FXCollections.observableArrayList();
        @FXML
            // This method is called by the FXMLLoader when initialization is complete
        void initialize()
        {
            assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert cbPackageId != null : "fx:id=\"cbPackageId\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert cbTripType != null : "fx:id=\"cbTripType\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfBookingDate != null : "fx:id=\"tfBookingDate\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfBookingId != null : "fx:id=\"tfBookingId\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfBookingNo != null : "fx:id=\"tfBookingNo\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert cbCustomerId != null : "fx:id=\"cbCustomerId\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfTravelerCount != null : "fx:id=\"tfTravelerCount\" was not injected: check your FXML file 'AddBooking-view.fxml'.";

            populateCustomerIdComboBox();
            populateTripTypeComboBox();
            populatePackageIdComboBox();

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

    private void closeDialog()
    {
        if (mainController != null)
        {
            mainController.clearTableSelections();
        }
        btnCancel.getScene().getWindow().hide();
    }

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


    public void setMainController(DashboardController mainController) {

        this.mainController = mainController;
    }

    private void populateCustomerIdComboBox()
    {
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
            ResultSet rs = stmt.executeQuery("select CustomerId from customers");

            while (rs.next())
            {
                String item = rs.getString("CustomerId");
                customers.add(item);
            }

            cbCustomerId.setItems(customers);
            conn.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
        private void populateTripTypeComboBox() {


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
                ResultSet rs = stmt.executeQuery("select TripTypeId from triptypes");

                while (rs.next())
                {
                    String item = rs.getString("TripTypeId");
                    tripTypes.add(item);
                }

                cbTripType.setItems(tripTypes);
                conn.close();
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private void populatePackageIdComboBox() {


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
                ResultSet rs = stmt.executeQuery("select * from packages");
                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next())
                {
                    String item = rs.getString("PackageId");
                    packages.add(rs);
                }
                cbPackageId.setItems(packages);
                conn.close();
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void passModeToDialog(String mode) {
            this.mode = mode;
        }

        public void processBooking(Booking booking)
        {
            tfBookingId.setText(booking.getBookingId() + "");
            tfBookingDate.setText(booking.getBookingDate() + "");
            tfBookingNo.setText(booking.getBookingNo());
            tfTravelerCount.setText(booking.getTravelerCount() + "");
            if(mode.equals("edit"))
            {

            }
        }

    private void btnSaveClicked(MouseEvent mouseEvent) {
        Properties p = getProperties();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection((String) p.get("url"), p);
            String sql = "";
            if(mode.equals("edit"))
            {
                sql = "UPDATE `bookings` SET `BookingDate`=?, `BookingNo`=?, `TravelerCount`=?, `CustomerId`=?, `TripTypeId`=?, " +
                        "`PackageId`=? WHERE BookingId =?";

            }
            else{
                sql = "INSERT INTO `bookings`(`BookingDate`, `BookingNo`, `TravelerCount`, `CustomerId`, `TripTypeId`, `PackageId`) " +
                        "VALUES (?,?,?,?,?,?)";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfBookingDate.getText());
            stmt.setString(2, tfBookingNo.getText());
            stmt.setDouble(3, Double.parseDouble(tfTravelerCount.getText()));
            stmt.setInt(4, Integer.parseInt(String.valueOf(cbCustomerId.getValue())));
            stmt.setString(5, String.valueOf(cbTripType.getValue()));
            stmt.setInt(6, Integer.parseInt(String.valueOf(cbPackageId.getValue())));

            if(mode.equals("edit"))
            {
                stmt.setInt(7, Integer.parseInt(tfBookingId.getText()));
            }
            int numRows = stmt.executeUpdate();
            if(numRows == 0)
            {
                System.out.println("Save Failed.");
            }
            else {
                System.out.println("Data saved Successfully.");
            }
            conn.close();
            // Close the dialog window when the save operation is successful
            closeDialog();

            // Refresh the product list in the dashboard
            mainController.refreshBookingList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
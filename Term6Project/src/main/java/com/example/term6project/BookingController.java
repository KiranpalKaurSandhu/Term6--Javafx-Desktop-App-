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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

        @FXML // fx:id="tfCustomerId"
        private TextField tfCustomerId; // Value injected by FXMLLoader

        @FXML // fx:id="tfTravelerCount"
        private TextField tfTravelerCount; // Value injected by FXMLLoader

        private String mode;

        @FXML
            // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert cbPackageId != null : "fx:id=\"cbPackageId\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert cbTripType != null : "fx:id=\"cbTripType\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfBookingDate != null : "fx:id=\"tfBookingDate\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfBookingId != null : "fx:id=\"tfBookingId\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfBookingNo != null : "fx:id=\"tfBookingNo\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfCustomerId != null : "fx:id=\"tfCustomerId\" was not injected: check your FXML file 'AddBooking-view.fxml'.";
            assert tfTravelerCount != null : "fx:id=\"tfTravelerCount\" was not injected: check your FXML file 'AddBooking-view.fxml'.";

            populateTripTypeComboBox();
            populatePackageIdComboBox();
        }

        private void populateTripTypeComboBox() {
            ObservableList tripTypes = FXCollections.observableArrayList();

            String url = "";
            String user = "";
            String password = "";

            try {
                FileInputStream fis = new FileInputStream("C:\\Users\\Jade-Laptop\\Documents\\connection.properties");
                Properties p = new Properties();
                p.load(fis);
                url = (String) p.get("url");
                user = (String) p.get("user");
                password = (String) p.get("password");

                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select TripTypeId from triptypes");

                while (rs.next()) {
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
            ObservableList packages = FXCollections.observableArrayList();

            String url = "";
            String user = "";
            String password = "";

            try {
                FileInputStream fis = new FileInputStream("C:\\Users\\Jade-Laptop\\Documents\\connection.properties");
                Properties p = new Properties();
                p.load(fis);
                url = (String) p.get("url");
                user = (String) p.get("user");
                password = (String) p.get("password");

                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from packages");
                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next()) {
                    packages.add(new Packages(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
                            rs.getString(5), rs.getDouble(6), rs.getDouble(7)));
                }

                conn.close();
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void passModeToDialog(String mode) {
            this.mode = mode;
        }

        public void processBooking(Booking booking) {
//        tfAgtId.setText(t1.getAgentId() + "");
//        tfAgtFirstName.setText(t1.getAgtFirstName());
//        tfAgtMiddleInitial.setText(t1.getAgtMiddleInitial());
//        tfAgtLastName.setText(t1.getAgtLastName());
//        tfAgtBusPhone.setText(t1.getAgtBusPhone());
//        tfAgtEmail.setText(t1.getAgtEmail());
//        tfAgtPosition.setText(t1.getAgtPosition());
//        tfAgencyId.setText(t1.getAgencyId() + "");
            tfBookingId.setText(booking.getBookingId() + "");
            tfBookingDate.setText(booking.getBookingDate() + "");
            tfBookingNo.setText(booking.getBookingNo());
            tfTravelerCount.setText(booking.getTravelerCount() + "");
            tfCustomerId.setText(booking.getCustomerId() + "");
        }
    }
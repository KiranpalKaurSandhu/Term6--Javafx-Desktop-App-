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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

import javafx.stage.Stage;

public class DashboardController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCustomer"
    private Button btnCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="btnPackage"
    private Button btnPackage; // Value injected by FXMLLoader

    @FXML // fx:id="btnProduct"
    private Button btnProduct; // Value injected by FXMLLoader

    @FXML // fx:id="btnSupplier"
    private Button btnSupplier; // Value injected by FXMLLoader

    @FXML // fx:id="colBookingDate"
    private TableColumn<Booking, Date> colBookingDate; // Value injected by FXMLLoader

    @FXML // fx:id="colBookingId"
    private TableColumn<Booking, Integer> colBookingId; // Value injected by FXMLLoader

    @FXML // fx:id="colBookingNo"
    private TableColumn<Booking, String> colBookingNo; // Value injected by FXMLLoader

    @FXML // fx:id="colCustomerId"
    private TableColumn<Booking, Integer> colCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="colPackageId"
    private TableColumn<Booking, Integer> colPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="colTravelerCount"
    private TableColumn<Booking, Double> colTravelerCount; // Value injected by FXMLLoader

    @FXML // fx:id="colTripTypeId"
    private TableColumn<Booking, String> colTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="tvBooking"
    private TableView<Booking> tvBooking; // Value injected by FXMLLoader

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

        }



    ObservableList<Booking> data = FXCollections.observableArrayList();
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCustomer != null : "fx:id=\"btnCustomer\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnPackage != null : "fx:id=\"btnPackage\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnProduct != null : "fx:id=\"btnProduct\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert btnSupplier != null : "fx:id=\"btnSupplier\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colBookingDate != null : "fx:id=\"colBookingDate\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colBookingId != null : "fx:id=\"colBookingId\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colBookingNo != null : "fx:id=\"colBookingNo\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colCustomerId != null : "fx:id=\"colCustomerId\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colPackageId != null : "fx:id=\"colPackageId\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colTravelerCount != null : "fx:id=\"colTravelerCount\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert colTripTypeId != null : "fx:id=\"colTripTypeId\" was not injected: check your FXML file 'Dashboard-view.fxml'.";
        assert tvBooking != null : "fx:id=\"tvBooking\" was not injected: check your FXML file 'Dashboard-view.fxml'.";

        colBookingId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingId"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory<Booking, Date>("bookingDate"));
        colBookingNo.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingNo"));
        colTravelerCount.setCellValueFactory(new PropertyValueFactory<Booking, Double>("travelerCount"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("customerId"));
        colTripTypeId.setCellValueFactory(new PropertyValueFactory<Booking, String>("tripTypeId"));
        colPackageId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("packageId"));

        tvBooking.setItems(data);

        getBookings();
        btnCustomer.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {

                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(TravelExpertsApplication.class.getResource("Customer-view.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Enter Data");
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });
        btnProduct.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {

                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(TravelExpertsApplication.class.getResource("Product-view.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Enter Data");
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        btnSupplier.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {

                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(TravelExpertsApplication.class.getResource("Supplier-view.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Enter Data");
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });
        btnPackage.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {

                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(TravelExpertsApplication.class.getResource("TravelPackage-view.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Enter Data");
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void getBookings()
    {
        data.clear();

        String url = "";
        String user = "";
        String password = "";

        try
        {
            FileInputStream fis = new FileInputStream("C:\\Users\\Kiran\\Documents\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");

            Connection conn = DriverManager.getConnection(url,user,password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Bookings");

            while(rs.next())
            {
                data.add(new Booking(rs.getInt(1), rs.getDate(2), rs.getString(3),
                        rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
            }
            conn.close();
        } catch (IOException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}

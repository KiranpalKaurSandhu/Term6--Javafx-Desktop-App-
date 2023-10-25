/**
 * Sample Skeleton for 'AddCustomer-view.fxml' Controller Class
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;


public class CustomerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="cbAgentid"
    private ComboBox<Integer> cbAgentid; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustAddress"
    private TextField tfCustAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustBusPhone"
    private TextField tfCustBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustCountry"
    private TextField tfCustCountry; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustEmail"
    private TextField tfCustEmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustFirstName"
    private TextField tfCustFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustHomePhone"
    private TextField tfCustHomePhone; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustLastName"
    private TextField tfCustLastName; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustPostal"
    private TextField tfCustPostal; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustProv"
    private TextField tfCustProv; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustity"
    private TextField tfCustCity; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerId"
    private TextField tfCustomerId; // Value injected by FXMLLoader

    private String mode;
    private DashboardController mainController;

    public void processCustomer(Customer cust) {
        tfCustomerId.setText(cust.getCustomerId()+"");
        tfCustFirstName.setText(cust.getCustFirstName());
        tfCustLastName.setText(cust.getCustLastName());
        tfCustAddress.setText(cust.getCustAddress());
        tfCustCity.setText(cust.getCustCity());
        tfCustProv.setText(cust.getCustProv());
        tfCustPostal.setText(cust.getCustPostal());
        tfCustCountry.setText(cust.getCustCountry());
        tfCustHomePhone.setText(cust.getCustHomePhone());
        tfCustBusPhone.setText(cust.getCustBusPhone());
        tfCustEmail.setText(cust.getCustEmail());
        cbAgentid.getSelectionModel().select(cust.getAgentId());
    }

    public void setMainController (DashboardController mainController){
        this.mainController = mainController;
    }
    public void passModeToDialog(String mode){
        this.mode = mode;
        if (mode.equals("add"))
        {
            btnDelete.setVisible(false);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert cbAgentid != null : "fx:id=\"cbAgentid\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustAddress != null : "fx:id=\"tfCustAddress\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustBusPhone != null : "fx:id=\"tfCustBusPhone\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustCountry != null : "fx:id=\"tfCustCountry\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustEmail != null : "fx:id=\"tfCustEmail\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustFirstName != null : "fx:id=\"tfCustFirstName\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustHomePhone != null : "fx:id=\"tfCustHomePhone\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustLastName != null : "fx:id=\"tfCustLastName\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustPostal != null : "fx:id=\"tfCustPostal\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustProv != null : "fx:id=\"tfCustProv\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustCity != null : "fx:id=\"tfCustity\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";
        assert tfCustomerId != null : "fx:id=\"tfCustomerId\" was not injected: check your FXML file 'AddCustomer-view.fxml'.";

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

        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnDeleteClicked(mouseEvent);
            }
        });

        getAgentComboBox();

    }//init

    private void getAgentComboBox() {
        Properties p = getProperties();
        Connection conn = null;

        try {
            conn = DriverManager.getConnection((String) p.get("url"), p);

            String sql = "SELECT AgentId FROM Agents";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cbAgentid.getItems().add(rs.getInt("AgentId"));
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void btnDeleteClicked(MouseEvent mouseEvent) {
        Properties p = getProperties();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection((String) p.get("url"), p);
            String sql = "delete from customers where CustomerId=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(tfCustomerId.getText()));
            int numRows = stmt.executeUpdate();
            if(numRows == 0)
            {
                System.out.println("Deletion failed");
            }
            else
            {
                System.out.println("Customer deleted");
            }
            conn.close();
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert =  new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Failed!");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    
    //save customer after adding or editing
    private void btnSaveClicked(MouseEvent mouseEvent) {
        Properties p = getProperties();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection((String) p.get("url"),p);
            String sql = "";

            if(mode.equals("edit"))
            {
                sql = "UPDATE `customers` SET `CustFirstName`=?," +
                        "`CustLastName`=?,`CustAddress`=?,`CustCity`=?,`CustProv`=?,`CustPostal`=?," +
                        "`CustCountry`=?,`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=?," +
                        "`AgentId`=? WHERE CustomerId =?";
            }
            else {
                sql = "INSERT INTO `customers`(`CustFirstName`, `CustLastName`, " +
                        "`CustAddress`, `CustCity`, `CustProv`, `CustPostal`, `CustCountry`, " +
                        "`CustHomePhone`, `CustBusPhone`, `CustEmail`, `AgentId`)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tfCustFirstName.getText());
            stmt.setString(2, tfCustLastName.getText());
            stmt.setString(3, tfCustAddress.getText());
            stmt.setString(4, tfCustCity.getText());
            stmt.setString(5, tfCustProv.getText());
            stmt.setString(6, tfCustPostal.getText());
            stmt.setString(7, tfCustCountry.getText());
            stmt.setString(8, tfCustHomePhone.getText());
            stmt.setString(9, tfCustBusPhone.getText());
            stmt.setString(10, tfCustEmail.getText());
            stmt.setInt(11,Integer.parseInt (cbAgentid.getValue().toString()));
           // stmt.setInt(11, cbAgentid.getValue());

            if (mode .equals("edit"))
            {
                stmt.setInt(12, Integer.parseInt(tfCustomerId.getText()));
            } 

            int numRows = stmt.executeUpdate();
            
            if (numRows == 0)
            {
                System.out.println("Save failed");
            }
            else
            {
                System.out.println("Data saved");
            }
            conn.close();
            closeDialog();
            mainController.updateCustomerList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeDialog() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }


}//class

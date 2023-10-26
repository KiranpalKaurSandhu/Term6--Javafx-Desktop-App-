/**
 * Sample Skeleton for 'AddPackage-view.fxml' Controller Class
 */

package com.example.term6project;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;//imported for start and end dates

import static java.lang.Integer.parseInt;

public class TravelPackageController {
    private String mode;    //!!!
    private DashboardController mainController; //!!!
    public void setMainController(DashboardController mainController) { //!!!

        this.mainController = mainController;
    }
    public void passModeToDialog(String mode) { //!!!
        this.mode = mode;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgencyCommission"
    private TextField txtAgencyCommission; // Value injected by FXMLLoader

    @FXML // fx:id="txtBasePrice"
    private TextField txtBasePrice; // Value injected by FXMLLoader

    @FXML // fx:id="txtDescription"
    private TextField txtDescription; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtPackageId"
    private TextField txtPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="txtPackageName"
    private TextField txtPackageName; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtAgencyCommission != null : "fx:id=\"txtAgencyCommission\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtBasePrice != null : "fx:id=\"txtBasePrice\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtDescription != null : "fx:id=\"txtDescription\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'AddPackage-view.fxml'.";

        // set add mode as default
        mode = "add";
    }

    public void passPackageAndMode(Packages currentPackage, String mode) {
        this.mode = mode;

        // check which mode is being used
        System.out.println("Mode: " + mode);
        if (mode.equals("edit")) {
            populateEditForm(currentPackage);
        //!!! go back and check whether this is needed} else {
            //clearFormFields(); // Clear the form fields for adding a new package
        }
    }
    public void populateEditForm(Packages currentPackage) {
        //testing
        System.out.println("Package Name: " + currentPackage.getPkgName());
        System.out.println("Start Date: " + currentPackage.getPkgStartDate());
        System.out.println("End Date: " + currentPackage.getPkgEndDate());

        //convert package ID to string
        String strPackageId = Integer.toString(currentPackage.getPackageId());
        //String testString = "test";


        //convert price and commission to string
        String strPrice = Double.toString(currentPackage.getPkgBasePrice());
        String strCommission = Double.toString(currentPackage.getPkgAgencyCommission());

        //convert dates to strings
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String strStartDate= formatter.format(currentPackage.getPkgStartDate());
        String strEndDate= formatter.format(currentPackage.getPkgEndDate());


        txtPackageId.setText(strPackageId);  //!!! why isn't this working
        txtPackageName.setText(currentPackage.getPkgName());
        txtStartDate.setText(strStartDate);
        txtEndDate.setText(strEndDate);
        txtDescription.setText(currentPackage.getPkgDesc());
        txtBasePrice.setText(strPrice);
        txtAgencyCommission.setText(strCommission);
    }

    /*public void clearFormFields() {
        // clear all text fields
    }*/ //!!! will decided later whether this method is needed

    @FXML
    private void btnCancelClicked(){    //close add/edit window
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        //!!! add confirmation dialog
    }

    @FXML
    private void btnSaveClicked(){
        // Check if mainController is not null before calling refreshPackageList    !!! testing
        if (this.mainController != null) {
            this.mainController.refreshPackageList();
        }

        // collect text field data
        String packageName = txtPackageName.getText();
        String startDate = txtStartDate.getText();
        String endDate = txtEndDate.getText();
        String description = txtDescription.getText();
        double basePrice = Double.parseDouble(txtBasePrice.getText());
        double agencyCommission = Double.parseDouble(txtAgencyCommission.getText());

        Properties p = getProperties();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection((String) p.get("url"), p);   // establish connection to db

            String sql = "";    // write entry to database
            if (mode.equals("edit"))
            {
                sql = "UPDATE `packages` SET `PkgName`=?," +
                        "`PkgStartDate`=?,`PkgEndDate`=?," +
                        "`PkgDesc`=?,`PkgBasePrice`=?," +
                        "`PkgAgencyCommission`=? WHERE PackageId=?";
             }
            else {
                sql = "INSERT INTO `packages`(`PkgName`," +
                        " `PkgStartDate`, `PkgEndDate`, `PkgDesc`," +
                        " `PkgBasePrice`, `PkgAgencyCommission`)" +
                        " VALUES (?,?,?,?,?,?)";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            // Set parameters based on the SQL statement
            stmt.setString(1, packageName);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setString(4, description);
            stmt.setDouble(5, basePrice);
            stmt.setDouble(6, agencyCommission);

            // get and set package id from selection
            if (mode.equals("edit")) {
                try {
                    int packageId = parseInt(txtPackageId.getText());
                    stmt.setInt(7, parseInt(txtPackageId.getText()));  // !!! is this the right parameter index
                } catch (NumberFormatException e) {
                    System.out.println("Package ID: " + txtPackageId.getText());// !!! testing
                    System.err.println("Invalid package ID input.");
                    return;
                }
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Package data saved successfully.");
            } else {
                // No rows were affected, something is amiss
                System.out.println("Package data failed to save.");
            }

            conn.close(); //close db connection

            // Refresh the product list in the dashboard
            mainController.refreshPackageList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // !!! confirmation dialog

        //close add/edit window
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

   private Properties getProperties() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\Users\\Alisa\\Documents\\connection.properties");  //!!! edit for users machine
            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

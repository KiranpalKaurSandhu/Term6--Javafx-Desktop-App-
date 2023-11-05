/*Authored by Greg Bevington, Fall 2023. This file includes the code for adding and updating rows in the
* database "travel packages" table. Along with those functions, controls for exiting the window, and filters for
* all user input. */
package com.example.term6project;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class TravelPackageController {
    @FXML
    public DatePicker dpEndDate;
    @FXML
    private DatePicker dpStartDate;
    private String mode;
    private DashboardController mainController;
    public void setMainController(DashboardController mainController) {

        this.mainController = mainController;
    }
    public void passModeToDialog(String mode) {
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
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert dpStartDate != null ;
        assert dpEndDate != null ;
        // set add mode as default
        mode = "add";
    }

    public void passPackageAndMode(Packages currentPackage, String mode) {
        this.mode = mode;

        // check which mode is being used
        System.out.println("Mode: " + mode);
        if (mode.equals("edit")) {
            populateEditForm(currentPackage);
        }
    }
    public void populateEditForm(Packages currentPackage) {
        //convert package ID to string
        String strPackageId = Integer.toString(currentPackage.getPackageId());

        //convert price and commission to string
        String strPrice = Double.toString(currentPackage.getPkgBasePrice());
        String strCommission = Double.toString(currentPackage.getPkgAgencyCommission());

        // set text fields
        txtPackageId.setText(strPackageId);
        txtPackageName.setText(currentPackage.getPkgName());
        txtDescription.setText(currentPackage.getPkgDesc());
        txtBasePrice.setText(strPrice);
        txtAgencyCommission.setText(strCommission);

        //Convert SQL Date to LocalDate and set date pickers
        java.util.Date utilStartDate = currentPackage.getPkgStartDate();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

        java.util.Date utilEndDate = currentPackage.getPkgEndDate();
        java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());

        dpStartDate.setValue(sqlStartDate.toLocalDate());
        dpEndDate.setValue(sqlEndDate.toLocalDate());

    }

    @FXML
    private void btnCancelClicked(){    //close add/edit window
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaveClicked(){
        // Check if mainController is not null before calling refreshPackageList
        if (this.mainController != null) {
            this.mainController.refreshPackageList();
        }

        // collect text field and date picker data

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String packageName = txtPackageName.getText();
        String description = txtDescription.getText();
        String basePrice = txtBasePrice.getText();
        String agencyCommission = txtAgencyCommission.getText();

        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();

        //reformat dates to strings for use in SQL
        String startDateString = startDate.format(dateFormatter);
        String endDateString = endDate.format(dateFormatter);

        //confirm whether user wishes to save
        if (showConfirmationDialog("Save", "Are you sure you want to save these changes?")) {
            if (isValidString(packageName, "package Name")&&
                    isLaterThan(endDate, startDate) &&
                    isValidString(description, "description")&&
                    isValidDouble("Base Price", basePrice)&&
                    isValidDouble("Agency Commission", agencyCommission)
            )
            {
                //convert base price and agency commission to doubles
                Double basePriceDouble = Double.parseDouble(basePrice);
                Double agencyCommissionDouble = Double.parseDouble(agencyCommission);

                Properties p = getProperties();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection((String) p.get("url"), p);   // establish connection to db

                    String sql = "";    // write entry to database
                    if (mode.equals("edit")) {
                        sql = "UPDATE `packages` SET `PkgName`=?," +
                                "`PkgStartDate`=?,`PkgEndDate`=?," +
                                "`PkgDesc`=?,`PkgBasePrice`=?," +
                                "`PkgAgencyCommission`=? WHERE PackageId=?";
                    } else {
                        sql = "INSERT INTO `packages`(`PkgName`," +
                                " `PkgStartDate`, `PkgEndDate`, `PkgDesc`," +
                                " `PkgBasePrice`, `PkgAgencyCommission`)" +
                                " VALUES (?,?,?,?,?,?)";
                    }

                    PreparedStatement stmt = conn.prepareStatement(sql);
                    // Set parameters based on the SQL statement
                    stmt.setString(1, packageName);
                    stmt.setString(2, startDateString);
                    stmt.setString(3, endDateString);
                    stmt.setString(4, description);
                    stmt.setDouble(5, basePriceDouble);
                    stmt.setDouble(6, agencyCommissionDouble);

                    // get and set package id from selection
                    if (mode.equals("edit")) {
                        try {
                            int packageId = parseInt(txtPackageId.getText());
                            stmt.setInt(7, parseInt(txtPackageId.getText()));
                        } catch (NumberFormatException e) {
                            System.out.println("Package ID error. Packaged ID is currently: " + txtPackageId.getText());
                            return;
                        }
                    }

                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Package data saved successfully.");
                        //close add/edit window
                        closeDialog();
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

                // confirmation dialog
                successNotification();

            }
        }
    }
    private void closeDialog() {
        if (mainController != null) {
            mainController.clearTableSelections();
        }
        btnCancel.getScene().getWindow().hide();
    }


    private void successNotification(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Package saved.");
            alert.setHeaderText("Package saved successfully.");
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

    private void failureNotification(String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Package failed to save.");
        alert.setHeaderText(null);
        alert.setContentText(content);
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

    private boolean showConfirmationDialog(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(okButton, cancelButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        alert.showAndWait().ifPresent(response -> {
            if (response == okButton){
            }
        });
        return (alert.getResult() == okButton);
    }

    //string filter for package names and descriptions
    private boolean isValidString(String string, String field){
        // add message, prevent from being added to db
        if (string == null){
            failureNotification("Package failed to save. "+ field + " field must not be empty.");
            return false;
        }
        else{
            return true;
        }
    }

    //date filter for start and end dates
    private boolean isValidDate(String date){
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";

        if (date == null){
            failureNotification("Package failed to save. Date field must not be empty.");
            return false;
        }
        else if(!date.matches(datePattern)){
            failureNotification("Package failed to save. Dates must be in YYYY-MM-dd format.");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean isLaterThan(LocalDate endDate, LocalDate startDate) {
        if (endDate.isAfter(startDate)){
            return true;
        } else if (endDate.isBefore(startDate)){
            failureNotification("Package failed to save. End date must be later than start date (maybe you've swapped their places?).");
            return false;
        } else {
            failureNotification("Package failed to save. End date must be later than start date (they are currently set to the same date).");
            return false;
        }
    }


    //double filter for price and agency commission
    private boolean isValidDouble(String type, String stringDouble){
        if (stringDouble == null){
            failureNotification("Package failed to save. "+type+" field must not be empty.");
            return false;
        }
        else {
            String regex = "^\\d+(\\.\\d{1,2})?$";
            if( stringDouble.matches(regex)){
                return true;
            }
            else{
                failureNotification("Package failed to save. "+type+ " field must be in currency format, e.g., '365.20'.");
                return false;
            }
        }
    }

   private Properties getProperties() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\Users\\PC1\\Documents\\connection.properties");  //!!! edit for users machine

            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

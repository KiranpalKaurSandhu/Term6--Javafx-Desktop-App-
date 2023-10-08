/**
 * Sample Skeleton for 'AddPackage-view.fxml' Controller Class
 */

package com.example.term6project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;//imported for start and end dates
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

    @FXML // fx:id="txtAgencyComission"
    private TextField txtAgencyComission; // Value injected by FXMLLoader

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
        assert txtAgencyComission != null : "fx:id=\"txtAgencyComission\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtBasePrice != null : "fx:id=\"txtBasePrice\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtDescription != null : "fx:id=\"txtDescription\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'AddPackage-view.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'AddPackage-view.fxml'.";

    }

    public void populateEditForm(Packages currentPackage) {
        //testing
        System.out.println("Package Name: " + currentPackage.getPkgName());
        System.out.println("Start Date: " + currentPackage.getPkgStartDate());
        System.out.println("End Date: " + currentPackage.getPkgEndDate());
        ///

        //convert price and commission to string //!!! did i misspell commission?
        String strPrice = Double.toString(currentPackage.getPkgBasePrice());
        String strCommission = Double.toString(currentPackage.getPkgAgencyCommission());

        //convert dates to strings
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strStartDate= formatter.format(currentPackage.getPkgStartDate());
        String strEndDate= formatter.format(currentPackage.getPkgEndDate());


        txtPackageId.setText(currentPackage.getPkgName());
        txtPackageName.setText(currentPackage.getPkgName());
        txtStartDate.setText(strStartDate);
        txtEndDate.setText(strEndDate);
        txtDescription.setText(currentPackage.getPkgDesc());
        txtBasePrice.setText(strPrice);
        txtAgencyComission.setText(strCommission);
    }

    @FXML
    private void cancelBtnClicked(){    //close add/edit window
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}

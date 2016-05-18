/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import domain.WebshopDriver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Niels
 */
public class RegisterController implements Initializable, ControlledScreen {

    private ScreensController controller;
    private boolean validEmail;
    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField streetNameTxt;
    @FXML
    private TextField houseNumberTxt;
    @FXML
    private TextField cityTxt;
    @FXML
    private TextField countryTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField phoneNumberTxt;
    @FXML
    private TextField birthDayTxt;
    @FXML
    private TextField birthMonthTxt;
    @FXML
    private TextField birthYearTxt;
    @FXML
    private Label formulaErrorTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private TextField zipCodeTxt;
    
    @FXML
    public void showCatalogueScreen() {
        controller.setScreen(CATALOGUE_SCREEN);
        controller.unloadScreen(REGISTER_SCREEN);
    }
    
    @FXML
    public void register() {
        String email = "", password = "", firstName = "", lastName = "", 
                streetName = "", houseNumber = "", city = "", zipCode = "", 
                country = "", phoneNumber = "", birthDay = "", birthMonth = "", birthYear = "";
        boolean error = false;
        formulaErrorTxt.setVisible(false);
        if(emailTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst en email.");
            error = true;
        }
        else if(validEmail) {
            email = emailTxt.getText();
        }
        else {
            checkEmail();
        }
        if(passwordTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst et password.");
            error = true;
        }
        else {
            password = passwordTxt.getText();
        }
        if(firstNameTxt.getText().isEmpty() || lastNameTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst både for- og efternavn.");
            error = true;
        }
        else {
            firstName = firstNameTxt.getText();
            lastName = lastNameTxt.getText();
        }
        if(streetNameTxt.getText().isEmpty() || houseNumberTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst både vejnavn og husnummer.");
            error = true;
        }
        else {
            streetName = streetNameTxt.getText();
            houseNumber = houseNumberTxt.getText();
        }
        if(cityTxt.getText().isEmpty() || zipCodeTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst både bynavn og postnummer.");
            error = true;
        }
        else {
            city = cityTxt.getText();
            zipCode = zipCodeTxt.getText();
        }
        if(countryTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst land.");
            error = true;
        }
        else {
            country = countryTxt.getText();
        }
        if(phoneNumberTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst et telefonnummer.");
            error = true;
        }
        else {
            phoneNumber = phoneNumberTxt.getText();
        }
        if(birthDayTxt.getText().isEmpty() || birthMonthTxt.getText().isEmpty() ||birthYearTxt.getText().isEmpty()) {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Indtast venligst en fødselsdag.");
            error = true;
        }
        else {
            birthDay = birthDayTxt.getText();
            birthMonth = birthMonthTxt.getText();
            birthYear = birthYearTxt.getText();
        }
        if(!error && validEmail) {
            WebshopDriver.getInstance().createUser(email, password, phoneNumber, 
                    firstName, lastName, houseNumber, streetName, zipCode, city, 
                    country, birthDay, birthMonth, birthYear);
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Bruger oprettet.");
        }
    }
    
    public void checkEmail() {
        if(WebshopDriver.getInstance().isValidEmail(emailTxt.getText())) {
            formulaErrorTxt.setVisible(false);
            validEmail = true;
        }
        else {
            formulaErrorTxt.setVisible(true);
            formulaErrorTxt.setText("Emailen er allerede taget.");
            validEmail = false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
}

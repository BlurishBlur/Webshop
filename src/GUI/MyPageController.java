package GUI;

import domain.IWebshopDriver;
import domain.WebshopDriver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * @author Niels
 */
public class MyPageController implements Initializable, ControlledScreen {
    
    private ScreensController controller;
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
    private TextField zipCodeTxt;
    
    @FXML
    public void showCatalogueScreen() {
        controller.setScreen(CATALOGUE_SCREEN);
        controller.unloadScreen(MYPAGE_SCREEN);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IWebshopDriver driver = WebshopDriver.getInstance();
        firstNameTxt.setText(driver.getFirstName());
        lastNameTxt.setText(driver.getLastName());
        streetNameTxt.setText(driver.getStreetName());
        houseNumberTxt.setText(driver.getHouseNumber());
        cityTxt.setText(driver.getCity());
        countryTxt.setText(driver.getCountry());
        zipCodeTxt.setText(driver.getZipCode());
        emailTxt.setText(driver.getEmail());
        phoneNumberTxt.setText(driver.getPhoneNumber());
        birthDayTxt.setText(driver.getBirthDay());
        birthMonthTxt.setText(driver.getBirthMonth());
        birthYearTxt.setText(driver.getBirthYear());
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}

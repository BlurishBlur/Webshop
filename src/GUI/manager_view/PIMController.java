package GUI.manager_view;

import GUI.ControlledScreen;
import GUI.ScreensController;
import domain.IWebshopDriver;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import domain.products.Product;
import domain.WebshopDriver;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * @author Niels
 */
public class PIMController implements Initializable, ControlledScreen {
    
    private ScreensController controller;
    private IWebshopDriver webshopDriver;
    private CheckBox[] genders, categories, manufacturers, colors;
    private RadioButton[] gendersRBtn, colorsRBtn;
    private final ObservableList SORTING_OPTIONS = FXCollections.observableArrayList(
            "Alfabetisk stigende", "Alfabetisk faldende", "Pris stigende", "Pris faldende");
    @FXML
    private TextField searchTxt;
    @FXML
    private CheckBox womenBox;
    @FXML
    private CheckBox menBox;
    @FXML
    private CheckBox unisexBox;
    @FXML
    private CheckBox sBox;
    @FXML
    private CheckBox mBox;
    @FXML
    private CheckBox lBox;
    @FXML
    private Slider priceSlider;
    private TextField priceTxt;
    @FXML
    private ComboBox<String> sortingOptionsBox;
    @FXML
    private TextField nameTxt;
    @FXML
    private RadioButton womenRBtn;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton menRBtn;
    @FXML
    private RadioButton unisexRBtn;
    @FXML
    private ComboBox<String> categoryCBox;
    @FXML
    private ComboBox<String> manufacturerCBox;
    @FXML
    private ListView<Product> productListView;
    @FXML
    private CheckBox sBoxNew;
    @FXML
    private CheckBox mBoxNew;
    @FXML
    private CheckBox lBoxNew;
    @FXML
    private VBox categoryChoiceContainer;
    @FXML
    private VBox manufacturerChoiceContainer;
    @FXML
    private TextField priceVTxt;
    @FXML
    private VBox colorChoiceContainer;
    @FXML
    private Button updateBtn;
    @FXML
    private Label errorTxt;
    @FXML
    private VBox priceContainer;
    @FXML
    private ComboBox<String> colorCBox;
    @FXML
    private TextArea descriptionArea;
    
    /**
     * Loader katalog skærmen, sætter den som vist, og unloader den nuværende skærm.
     */
    @FXML
    public void showCatalogueScreen() {
        controller.loadScreen(CATALOGUE_SCREEN, CATALOGUE_SCREEN_FXML);
        controller.setScreen(CATALOGUE_SCREEN);
        controller.unloadScreen(PIM_SCREEN);
    }
    
    /**
     * Ændrer produktdetaljerne, baseret på det der er indtastet i tekstfelterne.
     */
    @FXML
    private void changeProductDetails() {
        errorTxt.setText("");
        String name = nameTxt.getText();
        String category = categoryCBox.getEditor().getText();
        String manufacturer = manufacturerCBox.getEditor().getText();
        String color = colorCBox.getEditor().getText();
        String description = descriptionArea.getText();
        boolean small = sBoxNew.isSelected(), medium = mBoxNew.isSelected(), large = lBoxNew.isSelected();
        double price = Double.parseDouble(priceTxt.getText());
        if(name.isEmpty()) {
            errorTxt.setText("Indtast produktnavn.");
        }
        else if(category.isEmpty()) {
            errorTxt.setText("Indtast eller vælg kategori.");
        }
        else if(manufacturer.isEmpty()) {
            errorTxt.setText("Indtast eller vælg mærke.");
        }
        else if(color.isEmpty()) {
            errorTxt.setText("Indtast eller vælg farve.");
        }
        else if(!small && !medium && !large) {
            errorTxt.setText("Vælg mindst én størrelse.");
        }
        else if(description.isEmpty()) {
            errorTxt.setText("Indtast en beskrivelse.");
        }
        else if(priceTxt.getText().isEmpty()) {
            errorTxt.setText("Indtast en pris.");
        }
        else {
            webshopDriver.changeProductDetails(webshopDriver.getSelectedProduct().getId(), 
                name, category, small, medium, large, color, 
                ((RadioButton) genderGroup.getSelectedToggle()).getText(), 
                description, webshopDriver.getSelectedProduct().getImagePath(), 
                manufacturer, price);
            System.out.println("Produkt opdateret");
            createCategoryCheckBoxes();
            createManufacturerCheckBoxes();
            createColorCheckBoxes();
            updatePriceSlider();
            updateProductsShown();
        }
    }
    
    /**
     * Opdaterer listen af viste produkter.
     * 
     * Sørger for at alle produkterne bliver fjernet, og derefter tilføjet igen,
     * basseret på søgekriterierne.
     */
    @FXML
    private void updateProductsShown() {
        productListView.getItems().clear();
        errorTxt.setText("");
        webshopDriver.setSelectedProduct(null);
        boolean small = sBox.isSelected(), medium = mBox.isSelected(), large = lBox.isSelected();
        if(!small && !medium && !large) {
            small = true;
            medium = true;
            large = true;
        }
        List<Product> searchedProducts = webshopDriver.searchProducts(searchTxt.getText(), priceSlider.getValue(), 
            getSelectedText(genders), getSelectedText(categories), getSelectedText(manufacturers), getSelectedText(colors), small, medium, large);
        List<Product> sortedProducts = webshopDriver.sortProducts(sortingOptionsBox.getValue(), searchedProducts);
        productListView.getItems().addAll(sortedProducts);
        showProductInfo();
    }
    
    /**
     * Viser produktinfo omkring det valgte produkt.
     */
    private void showProductInfo() {
        errorTxt.setText("");
        if(webshopDriver.getSelectedProduct() == null) {
            nameTxt.setDisable(true);
            womenRBtn.setDisable(true);
            menRBtn.setDisable(true);
            unisexRBtn.setDisable(true);
            categoryCBox.setDisable(true);
            manufacturerCBox.setDisable(true);
            colorCBox.setDisable(true);
            sBoxNew.setDisable(true);
            mBoxNew.setDisable(true);
            lBoxNew.setDisable(true);
            descriptionArea.setDisable(true);
            priceTxt.setDisable(true);
            updateBtn.setDisable(true);
        }
        else {
            Product selectedProduct = webshopDriver.getSelectedProduct();
            nameTxt.setDisable(false);
            nameTxt.setText(selectedProduct.getName());
            for(RadioButton rBtn : gendersRBtn) {
                if(selectedProduct.getGender().equals(rBtn.getText())) {
                    genderGroup.selectToggle(rBtn);
                }
            }
            womenRBtn.setDisable(false);
            menRBtn.setDisable(false);
            unisexRBtn.setDisable(false);
            categoryCBox.setDisable(false);
            categoryCBox.setValue(selectedProduct.getCategory());
            manufacturerCBox.setDisable(false);
            manufacturerCBox.setValue(selectedProduct.getManufacturer());
            colorCBox.setDisable(false);
            colorCBox.setValue(selectedProduct.getColor());
            if(selectedProduct.isSmall()) {
                sBoxNew.setSelected(true);
            }
            if(selectedProduct.isMedium()) {
                mBoxNew.setSelected(true);
            }
            if(selectedProduct.isLarge()) {
                lBoxNew.setSelected(true);
            }
            sBoxNew.setDisable(false);
            mBoxNew.setDisable(false);
            lBoxNew.setDisable(false);
            descriptionArea.setDisable(false);
            descriptionArea.setText(selectedProduct.getDescription());
            priceTxt.setDisable(false);
            priceTxt.setText(selectedProduct.getPrice() + "");
            updateBtn.setDisable(false);
        }
    }
    
    /**
     * Bruges til at få de tekst der skal søges på, når der vælges et filter.
     * 
     * Sætter alle værdierne (true/false) fra CheckBoxene ind i en liste.
     * Hvis ingen er værdierne er true, bliver det opfattet som alle værdier
     * er true.
     * Ellers vælges kun de værdier der er true.
     * @param boxes Array af de CheckBoxe der skal søges igennem.
     * @return Værdierne der skal søges på med filter.
     */
    private Set<String> getSelectedText(CheckBox[] boxes) {
        List<Boolean> values = getValues(boxes);
        Set<String> text = new HashSet<>();
        if(values.contains(true)) {
            for(CheckBox cb : boxes) {
                if(cb.isSelected()) {
                    text.add(cb.getText());
                }
            }
        }
        else {
            for(CheckBox cb : boxes) {
                text.add(cb.getText());
            }
        }
        return text;
    }
    
    private List<Boolean> getValues(CheckBox[] boxes) {
        List<Boolean> values = new ArrayList<>();
        for(CheckBox cb : boxes) {
            values.add(cb.isSelected());
        }
        return values;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webshopDriver = WebshopDriver.getInstance();
        genders = new CheckBox[] {womenBox, menBox, unisexBox};
        gendersRBtn = new RadioButton[] {womenRBtn, menRBtn, unisexRBtn};
        createCategoryCheckBoxes();
        createManufacturerCheckBoxes();
        createColorCheckBoxes();
        updatePriceSlider();
        priceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            priceVTxt.setText(newValue.intValue() + "");
        });
        sortingOptionsBox.setItems(SORTING_OPTIONS);
        sortingOptionsBox.setValue("Alfabetisk stigende");
        categoryCBox.setItems(FXCollections.observableArrayList(webshopDriver.getAllCategories()));
        manufacturerCBox.setItems(FXCollections.observableArrayList(webshopDriver.getAllManufacturers()));
        colorCBox.setItems(FXCollections.observableArrayList(webshopDriver.getAllColors()));
        productListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Product> observable, Product oldValue, Product newValue) -> {
            if(newValue != null) {
                webshopDriver.setSelectedProduct(newValue);
                showProductInfo();
            }
        });
        productListView.setCellFactory((ListView<Product> param) -> {
            ListCell<Product> cell = new ListCell<Product>() {
                @Override protected void updateItem(Product product, boolean empty) {
                    super.updateItem(product, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(product.getName());
                    }
                }
            };
            return cell;
        });
        priceTxt = new TextField() {
            @Override public void replaceText(int start, int end, String text) {
                if(validate(text)) {
                    super.replaceText(start, end, text);
                }
            }
            @Override public void replaceSelection(String text) {
                if(validate(text)) {
                    super.replaceSelection(text);
                }
            }
            public boolean validate(String text) {
                return ("".equals(text) || text.matches("[0-9]"));
            }
        };
        priceContainer.getChildren().add(priceTxt);
        updateProductsShown();
        showProductInfo();
    }
    
    /**
     * Opdaterer priceslideren, til den højeste pris der findes i systemet.
     */
    private void updatePriceSlider() {
        priceSlider.setMax(webshopDriver.getMaxPrice());
        priceSlider.setValue(priceSlider.getMax());
        priceVTxt.setText(priceSlider.getMax() + "");
    }
    
    /**
     * Sletter eksisterende checkboxes, og opretter nye, baseret på hvilke kategorier
     * der findes i systemet.
     */
    private void createCategoryCheckBoxes() {
        categoryChoiceContainer.getChildren().clear();
        categories = new CheckBox[webshopDriver.getAllCategories().size()];
        int i = 0;
        for(String category : webshopDriver.getAllCategories()) {
            CheckBox categoryBox = new CheckBox(category);
            categoryBox.setOnAction((e) -> {
                updateProductsShown();
            });
            categoryChoiceContainer.getChildren().add(categoryBox);
            categories[i] = categoryBox;
            i++;
        }
    }
    
    /**
     * Sletter eksisterende checkboxes, og opretter nye, baseret på hvilke mærker
     * der findes i systemet.
     */
    private void createManufacturerCheckBoxes() {
        manufacturerChoiceContainer.getChildren().clear();
        manufacturers = new CheckBox[webshopDriver.getAllManufacturers().size()];
        int i = 0;
        for(String manufacturer : webshopDriver.getAllManufacturers()) {
            CheckBox manufacturerBox = new CheckBox(manufacturer);
            manufacturerBox.setOnAction((e) -> {
                updateProductsShown();
            });
            manufacturerChoiceContainer.getChildren().add(manufacturerBox);
            manufacturers[i] = manufacturerBox;
            i++;
        }
    }
    
    /**
     * Sletter eksisterende checkboxes, og opretter nye, baseret på hvilke farver
     * der findes i systemet.
     */
    private void createColorCheckBoxes() {
        colorChoiceContainer.getChildren().clear();
        colors = new CheckBox[webshopDriver.getAllColors().size()];
        int i = 0;
        for(String string : webshopDriver.getAllColors()) {
            CheckBox checkBox = new CheckBox(string);
            checkBox.setOnAction((e) -> {
                updateProductsShown();
            });
            colorChoiceContainer.getChildren().add(checkBox);
            colors[i] = checkBox;
            i++;
        }
    }
    
    /**
     * Sætter parent noden, så der nemt kan skiftes skærm.
     * @param screenParent Parent noden.
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}

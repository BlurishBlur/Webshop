package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import domain.products.Product;

/**
 * @author Niels
 */
public class ShoppingBasketItem extends HBox {
    
    private final BackgroundFill defaultBackgroundFill = new BackgroundFill(new Color(225 / 255.0, 225 / 255.0, 225 / 255.0, 1), null, null);
    private final Background defaultBackground = new Background(defaultBackgroundFill);
    
    private final BorderStroke hoverStroke = new BorderStroke(Color.GHOSTWHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
    private final Border hoverBorder = new Border(hoverStroke);
    
    public ShoppingBasketItem(Product product, int x, int y) {
        //setPadding(new Insets(15, 0, 0, 0));
        setSpacing(100);
        setPrefWidth(610);
        setPrefHeight(100);
        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.CENTER_LEFT);
        setBackground(defaultBackground);
        
        ImageView iv = new ImageView(new Image("file:icons/PHshirtIcon.png"));
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        Label name = new Label(product.getName());
        name.setTextAlignment(TextAlignment.LEFT);
        Label details = new Label(product.getSize() + "\n" + product.getColor() + "\n" + "2");
        details.setTextAlignment(TextAlignment.LEFT);
        Label price = new Label(product.getPrice() + "");
        price.setTextAlignment(TextAlignment.LEFT);
        getChildren().addAll(iv, name, details, price);
    }
}
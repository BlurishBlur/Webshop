package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import domain.products.Product;

/**
 * @author Niels
 */
public class ProductItem extends VBox {
    
    private final BackgroundFill defaultBackgroundFill = new BackgroundFill(new Color(225 / 255.0, 225 / 255.0, 225 / 255.0, 1), null, null);
    private final Background defaultBackground = new Background(defaultBackgroundFill);
    
    private final BorderStroke hoverStroke = new BorderStroke(Color.GHOSTWHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
    private final Border hoverBorder = new Border(hoverStroke);
    /**
     * 
     * @param product
     * @param x
     * @param y 
     */
    public ProductItem(Product product, int x, int y) {
        setPadding(new Insets(15, 0, 0, 0));
        setSpacing(20);
        setPrefWidth(130);
        setPrefHeight(160);
        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.TOP_CENTER);
        setBackground(defaultBackground);
        DropShadow ds = new DropShadow(2, new Color(0, 0, 0, 0.6));
        ds.setSpread(0.1);
        setEffect(ds);
        setOnMouseEntered((MouseEvent t) -> {
            setBorder(hoverBorder);
        });
        setOnMouseExited((MouseEvent t) -> {
            setBorder(null);
        });
        Label text = new Label(product.getName() + "\n" + product.getPrice() + " DKK");
        text.setTextAlignment(TextAlignment.CENTER);
        ImageView iv = new ImageView(new Image(product.getImagePath()));
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        getChildren().addAll(iv, text);
    }
}

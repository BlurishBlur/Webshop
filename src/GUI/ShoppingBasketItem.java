package GUI;

import domain.WebshopDriver;
import domain.products.Item;
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @author Niels
 */
public class ShoppingBasketItem extends HBox {
    
    private final BackgroundFill defaultBackgroundFill = new BackgroundFill(new Color(225 / 255.0, 225 / 255.0, 225 / 255.0, 1), null, null);
    private final Background defaultBackground = new Background(defaultBackgroundFill);
    
    private final BorderStroke hoverStroke = new BorderStroke(Color.GHOSTWHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
    private final Border hoverBorder = new Border(hoverStroke);
    
    TextField tf;
    Label price;
        
    public ShoppingBasketItem(Item item, int x, int y) {
        //setPadding(new Insets(15, 0, 0, 0));
        setSpacing(20);
        setPadding(new Insets(10,0,10,10));
        setPrefWidth(800);
        setPrefHeight(100);
        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.CENTER_LEFT);
        setBackground(defaultBackground);
        
        ImageView iv = new ImageView(new Image(item.getProduct().getImagePath()));
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        Label name = new Label(item.getProduct().getName());
        name.setTextAlignment(TextAlignment.LEFT);
        name.setMaxWidth(250);
        name.setMinWidth(250);
        Label details = new Label(/*item.getProduct().getSize() + */"\n" + item.getProduct().getColor());
        details.setTextAlignment(TextAlignment.LEFT);
        details.setMaxWidth(40);
        details.setMinWidth(40);
        price = new Label(item.getSumPrice() + "");
        price.setTextAlignment(TextAlignment.LEFT);
        price.setMaxWidth(70);
        price.setMinWidth(70);
        tf = new TextField(""+item.getQuantity());
        tf.setMaxWidth(40);
        Button update = new Button("Opdater antal");
        
        update.setOnAction(e -> {
            
            this.update(item);
        });
        
        Button rm = new Button("Fjern"); 
        
        rm.setOnAction(e -> {
            this.remove(item);
        });
        
        getChildren().addAll(iv, name, details, price, tf, update, rm);
    }
    
    private void remove(Item item){
        WebshopDriver.getInstance().removeItem(item);
        int i = ((VBox) this.getParent()).getChildren().indexOf(this)+1;
        ((VBox) this.getParent()).getChildren().remove(i);
        ((VBox) this.getParent()).getChildren().remove(this);    
    }
    
    private void update(Item item){
        String s = tf.getText();
        
        if(!s.equals("")){
            
            try{
               int i = Integer.valueOf(s);
               
               if(i == 0){
                   this.remove(item);
               }
               else {
                   item.changeQuantity(i);
                   price.setText(""+item.getSumPrice());
               }
               
            }
            catch(NumberFormatException e){ 
            }
            
        }
    }
}

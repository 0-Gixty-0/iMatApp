package imat;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

public class ShoppingCartListItem extends AnchorPane{
    private Product product;
    private Integer numItems;
    private MainViewController mainViewController;

    @FXML
    Label numItemsLabel;

    public ShoppingCartListItem(Product product, MainViewController mainViewController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingcart_item_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.mainViewController = mainViewController;
        this.numItems = 1;
        this.numItemsLabel.setText("1 st");
    }

    protected Integer getProductId(){
        return this.product.getProductId();
    }

    protected void updateNumItems(Integer numItems){
        this.numItems += 1;
        this.numItemsLabel.setText(String.format("%d st", numItems));
    }

}

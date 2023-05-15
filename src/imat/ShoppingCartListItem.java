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
    private MainViewController mainViewController;

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
    }

}

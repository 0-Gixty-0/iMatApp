package imat;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.text.DecimalFormat;

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

    @FXML
    ImageView shoppingCartImageView;

    @FXML
    Label shoppingCartItemNameLabel;

    @FXML
    Label shoppingCartPriceLabel;

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
        this.shoppingCartItemNameLabel.setText(product.getName());
        DecimalFormat df = new DecimalFormat("#.##");
        this.shoppingCartPriceLabel.setText(String.format("%s %s",df.format(product.getPrice()),product.getUnit()));
        String image_path = "file:" + "\\" + mainViewController.dataHandler.imatDirectory() + "\\" + "images" + "\\" + product.getImageName();
        this.shoppingCartImageView.setImage(new Image(image_path));
    }

    protected Integer getProductId(){
        return this.product.getProductId();
    }

    public void updateNumItemsLabel(Integer numItems){
        this.numItemsLabel.setText(String.format("%d st", numItems));
    }

    @FXML
    public void onClick(Event event){
        mainViewController.openDetailView(this.product);
    }

    @FXML
    public void onAdd(Event event) {
        this.numItems += 1;
        mainViewController.addItemToCart(this.product);}

    @FXML
    public void onRemove(Event event){
        if (this.numItems > 0)
            this.numItems -= 1;
        mainViewController.removeItemFromCart(this.product);
    }

}

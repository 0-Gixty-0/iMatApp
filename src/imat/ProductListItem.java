package imat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProductListItem extends AnchorPane {

    private Product product;
    private MainViewController mainViewController;
    private Integer numItems;

    @FXML
    ImageView productImageImageView;
    @FXML
    Label productNameLabel;
    @FXML
    Label productPrice;
    @FXML
    Label numItemsLabel;
    @FXML
    ImageView favoriteImageView;

    public ProductListItem(Product product, MainViewController mainViewController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imat_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.mainViewController = mainViewController;
        this.numItems = 0;
        this.numItemsLabel.setText(String.format("%d st",this.numItems));
        this.productNameLabel.setText(product.getName());
        DecimalFormat df = new DecimalFormat("#.##");
        this.productPrice.setText(String.format("%s %s",df.format(product.getPrice()),product.getUnit()));
        String image_path = "file:" + "\\" + mainViewController.dataHandler.imatDirectory() + "\\" + "images" + "\\" + product.getImageName();
        this.productImageImageView.setImage(new Image(image_path));
        if (mainViewController.dataHandler.isFavorite(this.product)){
            favoriteImageView.setImage(new Image("imat/resources/red_heart.png"));
        }
        System.out.println(image_path);

    }
    public Integer getProductId(){
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

    @FXML
    public void onFavorite(Event event){
        if (this.mainViewController.dataHandler.isFavorite(this.product)){
            this.favoriteImageView.setImage(new Image("imat/resources/empty_heart.png"));
            mainViewController.removeFavorite(this.product);
        } else {
            this.favoriteImageView.setImage(new Image("imat/resources/red_heart.png"));
            mainViewController.addFavorite(this.product);
        }

    }
}

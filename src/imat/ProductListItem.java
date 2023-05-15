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

    @FXML
    ImageView productImageImageView;
    @FXML
    Label productNameLabel;
    @FXML
    Label productPrice;

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
        this.productNameLabel.setText(product.getName());
        DecimalFormat df = new DecimalFormat("#.##");
        this.productPrice.setText(String.format("%s %s",df.format(product.getPrice()),product.getUnit()));
        String image_path = "file:" + "\\" + mainViewController.dataHandler.imatDirectory() + "\\" + "images" + "\\" + product.getImageName();
        this.productImageImageView.setImage(new Image(image_path));

    }
    @FXML
    public void onClick(Event event){
        mainViewController.openDetailView(this.product);
    }
}

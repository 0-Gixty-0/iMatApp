package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductListItem extends AnchorPane {

    private Product product;
    private MainViewController mainViewController;

    @FXML
    ImageView productImageImageView;
    @FXML
    Label productNameLabel;
    @FXML
    Label productCategory;
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


    }
}

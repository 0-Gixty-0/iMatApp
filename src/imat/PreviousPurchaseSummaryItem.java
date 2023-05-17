package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class PreviousPurchaseSummaryItem extends AnchorPane {
    MainViewController mainViewController;
    ShoppingItem item;
    Product product;

    @FXML
    ImageView summaryItemImageView;
    @FXML
    Label summaryItemPriceLabel;
    @FXML
    Label summaryItemAmountLabel;
    @FXML
    Label summaryItemTotalPriceLabel;
    @FXML
    Label summaryItemNameLabel;

    public PreviousPurchaseSummaryItem(ShoppingItem item, MainViewController mainViewController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousPurchasesItemListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainViewController = mainViewController;
        this.item = item;
        this.product = item.getProduct();
        String image_path = "file:" + "\\" + mainViewController.dataHandler.imatDirectory() + "\\" + "images" + "\\" + this.product.getImageName();
        this.summaryItemImageView.setImage(new Image(image_path));
        DecimalFormat df = new DecimalFormat("#.##");
        this.summaryItemPriceLabel.setText(String.format("%s %s",df.format(this.product.getPrice()),this.product.getUnit()));
        this.summaryItemAmountLabel.setText(String.format("%s st",String.valueOf(this.item.getAmount())));
        this.summaryItemTotalPriceLabel.setText(String.format("%s kr", String.valueOf(this.item.getTotal())));
        this.summaryItemNameLabel.setText(this.product.getName());
    }
}

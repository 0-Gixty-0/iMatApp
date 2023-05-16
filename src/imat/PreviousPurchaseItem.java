package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PreviousPurchaseItem extends AnchorPane {

    Order order;
    MainViewController mainViewController;
    @FXML
    Label dateLabel;
    @FXML
    Label numItemsLabel;

    public PreviousPurchaseItem(Order order, MainViewController mainViewController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imat_previous_purchase_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        this.mainViewController = mainViewController;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        this.dateLabel.setText(dateFormat.format(order.getDate()));
        this.numItemsLabel.setText(String.valueOf(order.getItems().size()));

    }
}

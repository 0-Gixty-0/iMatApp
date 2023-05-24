package imat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        System.out.println(order.getDate());
        this.dateLabel.setText(dateFormat.format(order.getDate()));
        double numProductsDouble = 0;
        for (ShoppingItem item : order.getItems()){
            numProductsDouble += item.getAmount();
        }
        int numProductsInt = (int) numProductsDouble;
        this.numItemsLabel.setText(String.format("%d st",numProductsInt));

    }
    @FXML
    public void onClick(Event event){
        mainViewController.openPreviousPurchaseSummaryOverlay(this.order);
    }
}

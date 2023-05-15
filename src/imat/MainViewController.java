
package imat;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

public class MainViewController implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();


    @FXML
    Label pathLabel;
    @FXML
    FlowPane generalItemsFlowPane;
    @FXML
    AnchorPane previousPurchasesOverlay;
    @FXML
    AnchorPane profileOverlay;
    @FXML
    AnchorPane shoppingCartOverlay;
    @FXML
    AnchorPane detailViewAnchorPane;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();

        // pathLabel.setText(iMatDirectory);

        // Initialize product list item map
        for (Product item : dataHandler.getProducts()) {
            ProductListItem productListItem = new ProductListItem(item, this);
            productListItemMap.put(item.getName(), productListItem);
        }

        updateProductListAll();

    }

    private void updateProductListAll(){
        generalItemsFlowPane.getChildren().clear();
        for (Product item : dataHandler.getProducts())
            generalItemsFlowPane.getChildren().add(productListItemMap.get(item.getName()));
    }
    // Update productlist with category
    private void updateProductListCategory(ProductCategory category){
        generalItemsFlowPane.getChildren().clear();
        for (Product item : dataHandler.getProducts(category))
            generalItemsFlowPane.getChildren().add(productListItemMap.get(item.getName()));
    }

    // Category Button Methods
    public void showDairyItems(){
        updateProductListCategory(ProductCategory.DAIRIES);
    }

    public void showMeatItems(){
        updateProductListCategory(ProductCategory.MEAT);
    }

    public void showFishItems(){
        updateProductListCategory(ProductCategory.FISH);
    }

    public void showVegetableItems(){
        updateProductListCategory(ProductCategory.VEGETABLE_FRUIT);
    }

    public void showFruitItems(){
        updateProductListCategory(ProductCategory.FRUIT);
    }

    public void showHerbItems(){
        updateProductListCategory(ProductCategory.HERB);
    }

    public void showPastaItems(){
        updateProductListCategory(ProductCategory.PASTA);
    }

    public void showNutsItems(){
        updateProductListCategory(ProductCategory.NUTS_AND_SEEDS);
    }

    public void showColdDrinksItems(){
        updateProductListCategory(ProductCategory.COLD_DRINKS);
    }

    public void showHotDrinksItems(){
        updateProductListCategory(ProductCategory.HOT_DRINKS);
    }

    public void showBakingItems(){
        updateProductListCategory(ProductCategory.FLOUR_SUGAR_SALT);
    }

    // Open / Close Overlays
    public void openPreviousPurchasesOverlay(){
        previousPurchasesOverlay.toFront();
    }

    public void closePreviousPurchasesOverlay(){
        previousPurchasesOverlay.toBack();
    }

    public void openProfileOverlay(){
        profileOverlay.toFront();
    }

    public void closeProfileOverlay(){
        profileOverlay.toBack();
    }

    public void openShoppingCartOverlay(){
        shoppingCartOverlay.toFront();
    }

    public void closeShoppingCartOverlay(){
        shoppingCartOverlay.toBack();
    }

    public void closeDetailView(){
        detailViewAnchorPane.toBack();
    }
    public void openDetailView(){
        detailViewAnchorPane.toFront();
    }

}
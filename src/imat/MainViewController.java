
package imat;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;

public class MainViewController implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private List<ShoppingCartListItem> shoppingCartListItems = new ArrayList<ShoppingCartListItem>();
    private Map<Integer, Integer> shoppingCartNumItemsMap = new HashMap<>();
    private Product currentProduct = new Product();
    private ShoppingCart shoppingCart = dataHandler.getShoppingCart();
    private Customer customer = dataHandler.getCustomer();
    private CreditCard creditCard = dataHandler.getCreditCard();
    private FilteredList<Product> flProduct;
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

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
    @FXML
    ImageView detailItemImageView;
    @FXML
    Label detailItemNameLabel;
    @FXML
    Label detailItemPriceLabel;
    @FXML
    FlowPane shoppingCartFlowPane;
    @FXML
    AnchorPane checkOutStepOneAnchorPane;
    @FXML
    AnchorPane checkOutStepTwoAnchorPane;
    @FXML
    AnchorPane checkOutStepThreeAnchorPane;
    @FXML
    AnchorPane checkOutThankYouAnchorPane;
    @FXML
    Label numItemsLabel;
    @FXML
    Label totalPriceLabel;
    @FXML
    Label detailNumItemsLabel;
    @FXML
    TextField customerFirstNameTextField;
    @FXML
    TextField customerLastNameTextField;
    @FXML
    TextField customerAddressTextField;
    @FXML
    TextField customerPostCodeTextField;
    @FXML
    TextField customerPhoneNumberTextField;
    @FXML
    TextField customerEmailTextField;
    @FXML
    TextField cardHolderNameTextField;
    @FXML
    TextField cardNumberTextField;
    @FXML
    Spinner cardExpirationMonthSpinner;
    @FXML
    Spinner cardExpirationYearSpinner;
    @FXML
    TextField cardVerificationCodeTextField;
    @FXML
    ComboBox cardTypeComboBox;
    @FXML
    TextField profileAddressTextField;
    @FXML
    TextField profilePostCodeTextField;
    @FXML
    TextField profileCardNameTextField;
    @FXML
    TextField profileFirstNameTextField;
    @FXML
    TextField profileLastNameTextField;
    @FXML
    TextField profileEmailTextField;
    @FXML
    TextField profilePhoneNumberTextField;
    @FXML
    TextField profileCardVerificationCodeTextField;
    @FXML
    Spinner profileCardExpirationMonthSpinner;
    @FXML
    Spinner profileCardExpirationYearSpinner;
    @FXML
    ComboBox profileCardTypeComboBox;
    @FXML
    TextField profileCardNumberTextField;
    @FXML
    FlowPane previousPurchasesFlowPane;
    @FXML
    AnchorPane previousPurchaseSummaryAnchorPane;
    @FXML
    FlowPane previousPurchaseSummaryFlowPane;
    @FXML
    TextField searchBarTextField;
    @FXML
    ComboBox searchCategoryComboBox;
    @FXML
    Button dairyButton;
    @FXML
    Button meatButton;
    @FXML
    Button fishButton;
    @FXML
    Button vegetablesButton;
    @FXML
    Button fruitButton;
    @FXML
    Button favoritesButton;
    @FXML
    Button bakedButton;
    @FXML
    Button herbsButton;
    @FXML
    Button pastaButton;
    @FXML
    Button teaButton;
    @FXML
    Button sodaButton;
    @FXML
    Button nutsButton;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();

        // pathLabel.setText(iMatDirectory);

        //Setup FlowPane
        generalItemsFlowPane.setHgap(4);
        generalItemsFlowPane.setVgap(7);

        shoppingCartFlowPane.setVgap(5);

        // Initialize product list item map and observable list
        for (Product item : dataHandler.getProducts()) {
            ProductListItem productListItem = new ProductListItem(item, this);
            productListItemMap.put(item.getName(), productListItem);
            productObservableList.add(item);
        }

        // Initialize change listeners
        customerFirstNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsInt(customerFirstNameTextField.getText())){
                    customer.setFirstName(t1);
                }
            }
        });

        customerLastNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsInt(customerLastNameTextField.getText())){
                    customer.setLastName(t1);
                }
            }
        });

        customerAddressTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setAddress(customerAddressTextField.getText());
            }
        });

        customerPostCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(customerPostCodeTextField.getText())){
                    customer.setPostCode(t1);
                }
            }
        });

        customerPhoneNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(customerPhoneNumberTextField.getText())){
                    customer.setPhoneNumber(t1);
                }
            }
        });

        customerEmailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (customerEmailTextField.getText().contains("@")) {
                    customer.setEmail(customerEmailTextField.getText());
                }
            }
        });

        cardHolderNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsInt(cardHolderNameTextField.getText())){
                    creditCard.setHoldersName(cardHolderNameTextField.getText());
                }
            }
        });

        cardNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(cardNumberTextField.getText())){
                    creditCard.setCardNumber(cardNumberTextField.getText());
                }
            }
        });

        SpinnerValueFactory<Integer> valueFactoryMonthSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1, 1);
        cardExpirationMonthSpinner.setValueFactory(valueFactoryMonthSpinner);
        cardExpirationMonthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                creditCard.setValidMonth(newValue);
            }
        });

        SpinnerValueFactory<Integer> valueFactoryYearSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(23, 30, 23, 1);
        cardExpirationYearSpinner.setValueFactory(valueFactoryYearSpinner);
        cardExpirationYearSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                creditCard.setValidYear(newValue);
            }
        });

        cardVerificationCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(cardVerificationCodeTextField.getText())){
                    creditCard.setVerificationCode(Integer.parseInt(cardVerificationCodeTextField.getText()));
                }
            }
        });

        cardTypeComboBox.getItems().addAll("Visa", "Mastercard", "American Express", "Discover");
        cardTypeComboBox.getSelectionModel().select("Visa");
        cardTypeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String t1) {
                creditCard.setCardType(t1);
            }
        });

        // Profile Buttons Initialize
        profileFirstNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsInt(profileFirstNameTextField.getText())){
                    customer.setFirstName(t1);
                }
            }
        });

        profileLastNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsInt(profileLastNameTextField.getText())){
                    customer.setLastName(t1);
                }
            }
        });

        profileAddressTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (profileAddressTextField.getText().contains("@"))
                    customer.setAddress(profileAddressTextField.getText());
            }
        });

        profilePostCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(profilePostCodeTextField.getText())){
                    customer.setPostCode(t1);
                }
            }
        });

        profilePhoneNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(profilePhoneNumberTextField.getText())){
                    customer.setPhoneNumber(t1);
                }
            }
        });

        profileEmailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setEmail(profileEmailTextField.getText());
            }
        });

        profileCardNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsInt(profileCardNameTextField.getText())){
                    creditCard.setHoldersName(profileCardNameTextField.getText());
                }
            }
        });

        profileCardNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(profileCardNumberTextField.getText())){
                    creditCard.setCardNumber(profileCardNumberTextField.getText());
                }
            }
        });

        profileCardExpirationMonthSpinner.setValueFactory(valueFactoryMonthSpinner);
        profileCardExpirationMonthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                creditCard.setValidMonth(newValue);
            }
        });

        profileCardExpirationYearSpinner.setValueFactory(valueFactoryYearSpinner);
        profileCardExpirationYearSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                creditCard.setValidYear(newValue);
            }
        });

        profileCardVerificationCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!containsChar(profileCardVerificationCodeTextField.getText())){
                    creditCard.setVerificationCode(Integer.parseInt(profileCardVerificationCodeTextField.getText()));
                }
            }
        });

        profileCardTypeComboBox.getItems().addAll("Visa", "Mastercard", "American Express", "Discover");
        profileCardTypeComboBox.getSelectionModel().select("Visa");
        profileCardTypeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String t1) {
                creditCard.setCardType(t1);
            }
        });

        // Initialize Searchbar
        this.flProduct = new FilteredList(productObservableList, p -> true);
        //searchCategoryComboBox.getItems().addAll("Namn", "Kategori");
        //searchCategoryComboBox.setValue("Namn");

        searchBarTextField.setPromptText("Sök efter en produkt...");
        searchBarTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            //switch (searchCategoryComboBox.getValue().toString())//Switch on choiceBox value
            //{
            //    case "Namn":
            //        flProduct.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
            //        break;
            //}
            flProduct.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));
            updateProductListSearch();
        });


        updateShoppingCartLabels();

        updateProductListAll();

    }

    private void updateProductListAll(){
        generalItemsFlowPane.getChildren().clear();
        for (Product item : dataHandler.getProducts())
            generalItemsFlowPane.getChildren().add(productListItemMap.get(item.getName()));
    }
    // Update productlist with category
    private void updateProductListCategory(ProductCategory category){
        resetButtons();
        generalItemsFlowPane.getChildren().clear();
        for (Product item : dataHandler.getProducts(category))
            generalItemsFlowPane.getChildren().add(productListItemMap.get(item.getName()));
    }

    private void updateProductListFavorites(){
        resetButtons();
        generalItemsFlowPane.getChildren().clear();
        for (Product item : dataHandler.favorites()){
            generalItemsFlowPane.getChildren().add(productListItemMap.get(item.getName()));
        }
    }

    private void updateProductListSearch(){
        resetButtons();
        generalItemsFlowPane.getChildren().clear();
        for (Product item : flProduct){
            generalItemsFlowPane.getChildren().add(productListItemMap.get(item.getName()));
        }
    }

    private void updateShoppingCart(){
        shoppingCartFlowPane.getChildren().clear();
        for (ShoppingCartListItem item : shoppingCartListItems) {
            shoppingCartFlowPane.getChildren().add(item);
        }
    }

    private void updatePreviousPurchases(){
        previousPurchasesFlowPane.getChildren().clear();
        for (Order order : dataHandler.getOrders()){
            previousPurchasesFlowPane.getChildren().add(new PreviousPurchaseItem(order, this));
        }
    }

    private void updatePreviousPurchaseSummary(Order order){
        previousPurchaseSummaryFlowPane.getChildren().clear();
        for (ShoppingItem item : order.getItems()){
            previousPurchaseSummaryFlowPane.getChildren().add(new PreviousPurchaseSummaryItem(item, this));
        }
    }

    public void placeOrder(){
        dataHandler.placeOrder();
        emptyCart();
        checkOutStepThreeAnchorPane.toBack();
        checkOutThankYouAnchorPane.toFront();
    }

    //Populators
    private void populateItemDetailView(Product product){
        String image_path = "file:" + "\\" + dataHandler.imatDirectory() + "\\" + "images" + "\\" + product.getImageName();
        detailItemImageView.setImage(new Image(image_path));
        detailItemNameLabel.setText(product.getName());
        DecimalFormat df = new DecimalFormat("#.##");
        detailItemPriceLabel.setText(String.format("%s %s",df.format(product.getPrice()),product.getUnit()));

        if (shoppingCartNumItemsMap.containsKey(product.getProductId())) {
            detailNumItemsLabel.setText(String.format("%d st", shoppingCartNumItemsMap.get(product.getProductId())));
        } else {
            detailNumItemsLabel.setText("0 st");
        }

    }

    private void populateCheckoutStepOne(){
        customerFirstNameTextField.setText(customer.getFirstName());
        customerLastNameTextField.setText(customer.getLastName());
        customerPostCodeTextField.setText(customer.getPostCode());
        customerPhoneNumberTextField.setText(customer.getPhoneNumber());
        customerEmailTextField.setText(customer.getEmail());
        customerAddressTextField.setText(customer.getAddress());
    }

    private void populateCheckoutStepThree(){
        cardHolderNameTextField.setText(creditCard.getHoldersName());
        cardNumberTextField.setText(creditCard.getCardNumber());
        cardExpirationMonthSpinner.getValueFactory().setValue(creditCard.getValidMonth());
        cardExpirationYearSpinner.getValueFactory().setValue(creditCard.getValidYear());
        cardVerificationCodeTextField.setText(String.valueOf(creditCard.getVerificationCode()));
        cardTypeComboBox.setValue(creditCard.getCardType());
    }

    private void populateProfile(){
        profileFirstNameTextField.setText(customer.getFirstName());
        profileLastNameTextField.setText(customer.getLastName());
        profilePostCodeTextField.setText(customer.getPostCode());
        profilePhoneNumberTextField.setText(customer.getPhoneNumber());
        profileEmailTextField.setText(customer.getEmail());
        profileAddressTextField.setText(customer.getAddress());
        profileCardNameTextField.setText(creditCard.getHoldersName());
        profileCardNumberTextField.setText(creditCard.getCardNumber());
        profileCardExpirationMonthSpinner.getValueFactory().setValue(creditCard.getValidMonth());
        profileCardExpirationYearSpinner.getValueFactory().setValue(creditCard.getValidYear());
        profileCardVerificationCodeTextField.setText(String.valueOf(creditCard.getVerificationCode()));
        profileCardTypeComboBox.setValue(creditCard.getCardType());
    }

    // Category Button Methods
    private void resetButtons(){
        dairyButton.setStyle("category-color: #FFDBD1");
        meatButton.setStyle("category-color: #FFDBD1");
        fishButton.setStyle("category-color: #FFDBD1");
        herbsButton.setStyle("category-color: #FFDBD1");
        teaButton.setStyle("category-color: #FFDBD1");
        sodaButton.setStyle("category-color: #FFDBD1");
        favoritesButton.setStyle("category-color: #FFDBD1");
        vegetablesButton.setStyle("category-color: #FFDBD1");
        fruitButton.setStyle("category-color: #FFDBD1");
        bakedButton.setStyle("category-color: #FFDBD1");
        pastaButton.setStyle("category-color: #FFDBD1");
        nutsButton.setStyle("category-color: #FFDBD1");
    }
    public void showDairyItems(){
        updateProductListCategory(ProductCategory.DAIRIES);
        dairyButton.setStyle("category-color: #FCB8A6");
    }

    public void showMeatItems(){
        updateProductListCategory(ProductCategory.MEAT);
        meatButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showFishItems(){
        updateProductListCategory(ProductCategory.FISH);
        fishButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showVegetableItems(){
        updateProductListCategory(ProductCategory.VEGETABLE_FRUIT);
        vegetablesButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showFruitItems(){
        updateProductListCategory(ProductCategory.FRUIT);
        fruitButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showHerbItems(){
        updateProductListCategory(ProductCategory.HERB);
        herbsButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showPastaItems(){
        updateProductListCategory(ProductCategory.PASTA);
        pastaButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showNutsItems(){
        updateProductListCategory(ProductCategory.NUTS_AND_SEEDS);
        nutsButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showColdDrinksItems(){
        updateProductListCategory(ProductCategory.COLD_DRINKS);
        sodaButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showHotDrinksItems(){
        updateProductListCategory(ProductCategory.HOT_DRINKS);
        teaButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showBakingItems(){
        updateProductListCategory(ProductCategory.FLOUR_SUGAR_SALT);
        bakedButton.setStyle("-fx-background-color: #FCB8A6");
    }

    public void showFavorites(){
        updateProductListFavorites();
        favoritesButton.setStyle("-fx-background-color: #FCB8A6");
    }

    // Open / Close Overlays
    public void openPreviousPurchasesOverlay(){
        updatePreviousPurchases();
        previousPurchasesOverlay.toFront();
    }

    public void closePreviousPurchasesOverlay(){
        previousPurchasesOverlay.toBack();
    }

    public void openProfileOverlay(){
        populateProfile();
        profileOverlay.toFront();
    }

    public void closeProfileOverlay(){
        profileOverlay.toBack();
    }

    public void openShoppingCartOverlay(){
        updateShoppingCart();
        shoppingCartOverlay.toFront();
    }

    public void closeShoppingCartOverlay(){
        shoppingCartOverlay.toBack();
    }

    public void closeDetailView(){
        detailViewAnchorPane.toBack();
    }
    public void openDetailView(Product product){
        this.currentProduct = product;
        populateItemDetailView(product);
        detailViewAnchorPane.toFront();
    }

    public void openCheckoutStep1(){
        populateCheckoutStepOne();
        checkOutStepOneAnchorPane.toFront();
    }

    public void closeCheckoutStep1(){
        checkOutStepOneAnchorPane.toBack();
    }

    public void openCheckoutStep2(){
        checkOutStepTwoAnchorPane.toFront();
    }

    public void closeCheckoutStep2(){
        checkOutStepTwoAnchorPane.toBack();
    }

    public void openCheckoutStep3(){
        populateCheckoutStepThree();
        checkOutStepThreeAnchorPane.toFront();
    }

    public void closeCheckoutStep3(){
        checkOutStepThreeAnchorPane.toBack();
    }

    public void openCheckoutThankYou(){
        checkOutThankYouAnchorPane.toFront();
    }

    public void closeCheckoutThankYou(){
        checkOutThankYouAnchorPane.toBack();
    }

    public void openPreviousPurchaseSummaryOverlay(Order order){
        updatePreviousPurchaseSummary(order);
        previousPurchaseSummaryAnchorPane.toFront();
    }

    public void closePreviousPurchaseSummaryOverlay(){
        previousPurchaseSummaryAnchorPane.toBack();
    }

    private void updateNumItemsLabels(){
        for (ProductListItem listItem : productListItemMap.values()){
            listItem.updateNumItemsLabel(shoppingCartNumItemsMap.getOrDefault(listItem.getProductId(), 0));
        }
        for (ShoppingCartListItem listItem : shoppingCartListItems){
            listItem.updateNumItemsLabel(shoppingCartNumItemsMap.getOrDefault(listItem.getProductId(), 0));
        }
        if (shoppingCartNumItemsMap.containsKey(this.currentProduct.getProductId())) {
            detailNumItemsLabel.setText(String.format("%d st", shoppingCartNumItemsMap.get(this.currentProduct.getProductId())));
        } else {
            detailNumItemsLabel.setText("0 st");
        }

    }

    private void updateShoppingCartLabels() {
        double numItemsDouble = (double) 0;
        for (ShoppingItem item : shoppingCart.getItems()){
            numItemsDouble += item.getAmount();
        }
        int numItemsInt = (int) numItemsDouble;
        numItemsLabel.setText(String.format("Antal Varor: %d", numItemsInt));
        DecimalFormat df = new DecimalFormat("#.##");
        totalPriceLabel.setText(String.format("Total Pris: %s kr", df.format(shoppingCart.getTotal())));
    }

    public void addItemToCart(Product product){
        if (shoppingCartNumItemsMap.containsKey(product.getProductId())){
            shoppingCartNumItemsMap.replace(product.getProductId(), shoppingCartNumItemsMap.get(product.getProductId()) + 1);
        } else {
            ShoppingCartListItem listItem = new ShoppingCartListItem(product, this);
            shoppingCartNumItemsMap.put(product.getProductId(), 1);
            shoppingCartListItems.add(listItem);
        }
        shoppingCart.addProduct(product);
        updateNumItemsLabels();
        updateShoppingCartLabels();
    }

    public void removeItemFromCart(Product product){
        if (shoppingCartNumItemsMap.containsKey(product.getProductId())){
            if (shoppingCartNumItemsMap.get(product.getProductId()) > 1) {
                shoppingCartNumItemsMap.replace(product.getProductId(), shoppingCartNumItemsMap.get(product.getProductId()) - 1);
            }
            else {
                shoppingCartNumItemsMap.remove(product.getProductId());
                shoppingCartListItems.removeIf(item -> item.getProductId() == product.getProductId());
            }
        }
        for (ShoppingItem currentItem : this.shoppingCart.getItems()) {
            if (currentItem.getProduct().getProductId() == product.getProductId() && currentItem.getAmount() > 0) {
                Double numInCart = currentItem.getAmount();
                shoppingCart.removeProduct(product);
                shoppingCart.addProduct(product, numInCart - 1);
            } else {
                shoppingCart.removeProduct(product);
            }
        }
        updateNumItemsLabels();
        updateShoppingCartLabels();
    }

    protected void addFavorite(Product product){
        dataHandler.addFavorite(product);
    }

    protected void removeFavorite(Product product){
        dataHandler.removeFavorite(product);
    }

    @FXML
    public void emptyCart(){
        shoppingCartListItems.clear();
        shoppingCartNumItemsMap.clear();
        shoppingCart.clear();
        updateNumItemsLabels();
        updateShoppingCart();
        updateShoppingCartLabels();
    }

    @FXML
    public void removeDetailView(Event event){
        removeItemFromCart(this.currentProduct);
    }

    @FXML
    public void addDetailView(Event event){
        addItemToCart(this.currentProduct);
    }

    //Customer Code

    private boolean containsInt(String text){
        char[] chars = text.toCharArray();
        for (char c : chars){
            if (Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private boolean containsChar(String text) {
        char[] chars = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.length() != chars.length;
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }


}
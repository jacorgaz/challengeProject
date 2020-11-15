package pages;

import org.openqa.selenium.By;
import java.util.HashMap;


public class ProductPage extends BaseCommands {
    /*
     * Page Elements
     */
    private final By BUTTON_ADD_QUANTITY = By.xpath("//*[@class='icon-quantity-plus']");
    private final By TEXT_QUANTITY_TO_ADD = By.xpath("//*[contains(@class, 'txtCounter')]");
    private final By BUTTON_ADD_TO_SHOPPING_CART = By.xpath("//*[@class='addtocart']");
    private final By TEXT_PRODUCT_NAME =  By.xpath("//*[@class='producttitle']");

    HashMap<String, Integer> productNameAndUnitsAddedToCart = new HashMap<>();
    private int unitsOfProductAddedToShoppingCart =0;

    protected int getUnitsOfProductAddedToShoppingCart() {
        return unitsOfProductAddedToShoppingCart;
    }

    private void setUnitsOfProductAddedToShoppingCart(int unitsOfProductAddedToShoppingCart) {
        this.unitsOfProductAddedToShoppingCart = getUnitsOfProductAddedToShoppingCart()+ unitsOfProductAddedToShoppingCart;
    }

    protected void addNumberOfUnitsToProduct() {
        int min = 1;
        int max = 6;
        int unitsToAdd = (int) (Math.random() * (max - min + 1) + 1); //Add number of units we want to add to the product per test run
        int unitsAdded =0;
        String productName = getText(TEXT_PRODUCT_NAME);

        while (unitsAdded!=unitsToAdd){
        clickElement(BUTTON_ADD_QUANTITY);
            unitsAdded= Integer.parseInt(getValue(TEXT_QUANTITY_TO_ADD));
        }
        setUnitsOfProductAddedToShoppingCart(unitsToAdd);
        productNameAndUnitsAddedToCart.put(productName,unitsToAdd);
    }

    protected void addProductToShoppingCart() {
        clickElement(BUTTON_ADD_TO_SHOPPING_CART);
    }
}

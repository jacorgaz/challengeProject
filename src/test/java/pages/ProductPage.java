package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.ExtentReportManager;

import java.util.HashMap;


public class ProductPage extends BaseCommands {

    private final By BUTTON_ADD_QUANTITY = By.xpath("//*[@class='icon-quantity-plus']");
    private final By TEXT_QUANTITY_TO_ADD = By.xpath("//*[contains(@class, 'txtCounter')]");
    private final By BUTTON_ADD_TO_SHOPPING_CART = By.xpath("//*[@class='addtocart']");
    private final By TEXT_UNITS_ADDED_TO_SHOPPING_CART = By.id("cart_quantity");
    private final By TEXT_PRODUCT_NAME =  By.xpath("//*[@class='producttitle']");

    HashMap<Integer, String> productNameAndUnitsAdded = new HashMap<>();
    ExtentTest extentTest = ExtentReportManager.getExtentTest();

    private int unitsOfProductAddedToShoppingCart =0;

    public int getUnitsOfProductAddedToShoppingCart() {
        return unitsOfProductAddedToShoppingCart;
    }

    public void setUnitsOfProductAddedToShoppingCart(int unitsOfProductAddedToShoppingCart) {
        this.unitsOfProductAddedToShoppingCart = getUnitsOfProductAddedToShoppingCart()+ unitsOfProductAddedToShoppingCart;
    }

    public void addProductQuantity() {
        int min = 1;
        int max = 6;
        int unitsToAdd = (int) (Math.random() * (max - min + 1) + 1);
        int unitsAdded =0;
        String productName = getText(TEXT_PRODUCT_NAME);

        while (unitsAdded!=unitsToAdd){
        clickElement(BUTTON_ADD_QUANTITY);
            unitsAdded= Integer.parseInt(getValue(TEXT_QUANTITY_TO_ADD));
        }
        setUnitsOfProductAddedToShoppingCart(unitsToAdd);
        productNameAndUnitsAdded.put(unitsToAdd,productName);
    }

    public void addToShoppingCart() {
        clickElement(BUTTON_ADD_TO_SHOPPING_CART);
    }

    public void userVerifyNumberOfUnitsAddedToShoppingCart() {
        int numberOfUnitsAddedInCart =  Integer.parseInt(getText(TEXT_UNITS_ADDED_TO_SHOPPING_CART));
        Assert.assertEquals(getUnitsOfProductAddedToShoppingCart(), numberOfUnitsAddedInCart);
    }
}

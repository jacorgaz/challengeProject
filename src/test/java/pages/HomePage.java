package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import tests.BaseTestController;
import utils.ExtentReportManager;
import utils.PropertyManager;
import java.io.IOException;


public class HomePage extends BaseCommands {
    /*
     * Page Elements
     */
    private final By LIST_PRODUCTS = By.xpath("//*[contains(@class, 'product-box lslide')]");
    private final By BUTTON_CLOSE_COOKIES = By.xpath("//*[@class='close__banner icon-close']");
    private final By BUTTON_CART = By.id("checkoutBtnCart");
    private final By TEXT_UNITS_ADDED_TO_SHOPPING_CART = By.id("cart_quantity");

    ExtentTest extentTest = ExtentReportManager.getExtentTest();
    private int numberOfItemsAddedToChart=0;

    public void userCloseCookiesBanner() {
        clickElement(BUTTON_CLOSE_COOKIES);
    }


    public void userAddProductsToShoppingCart() throws IOException {
        int numberOfItemsToAddToChart = Integer.parseInt(PropertyManager.getInstance().getNumberOfItemsToPurchase()); //Number of products wished in configuration.properties
        ProductPage productPage = BaseTestController.getHomePage();
        while (numberOfItemsAddedToChart<numberOfItemsToAddToChart) {
            clickElementByIndex(LIST_PRODUCTS, numberOfItemsAddedToChart);
            productPage.addNumberOfUnitsToProduct();
            productPage.addProductToShoppingCart();
            numberOfItemsAddedToChart++;
            navigateBack();
        };
    }

    /**
     * This method is used to Verify that the number of total units added,
     * is the same as the quantity displayed in cart.
     */

    public void userVerifyInBubbleCartNumberOfUnitsAdded() {
        ProductPage productPage = BaseTestController.getHomePage();
        int numberOfUnitsAddedInCart =  Integer.parseInt(getText(TEXT_UNITS_ADDED_TO_SHOPPING_CART));
        Assert.assertEquals(productPage.getUnitsOfProductAddedToShoppingCart(), numberOfUnitsAddedInCart);
    }

    public void useGoToCart() {
        clickElement(BUTTON_CART);
    }
}

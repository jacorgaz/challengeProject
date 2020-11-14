package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import utils.DriverManager;
import utils.ExtentReportManager;
import utils.PropertyManager;


public class HomePage extends BaseCommands {
    private final ExtentTest extentReport = ExtentReportManager.getExtentTest();
    private final WebDriver page = DriverManager.getDriver();
    private final int numberOfItemsToAddToChart = Integer.parseInt(PropertyManager.getInstance().getNumberOfItemsToPurchase());
    private int numberOfItemsAddedToChart=0;
    private final By LIST_PRODUCTS = By.xpath("//*[contains(@class, 'product-box lslide')]");
    private final By BUTTON_CLOSE_COOKIES = By.xpath("//*[@class='close__banner icon-close']");
    private final By BUTTON_CART = By.id("checkoutBtnCart");

    public void closeCookiesBanner() {
        clickElement(BUTTON_CLOSE_COOKIES);
    }

    public void userClickOnCart () {
        clickElement(BUTTON_CART);
    }

    public void selectProduct() {
        ProductPage productPage = BaseTest.getHomePage();
        while (numberOfItemsAddedToChart<numberOfItemsToAddToChart) {
            clickElementByIndex(LIST_PRODUCTS, numberOfItemsAddedToChart);
            productPage.addProductQuantity();
            productPage.addToShoppingCart();
            numberOfItemsAddedToChart++;
            navigateBack();
        }
        productPage.userVerifyNumberOfUnitsAddedToShoppingCart();
    }

}

package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import tests.BaseTest;
import utils.ExtentReportManager;
import utils.PropertyManager;

import java.util.HashMap;


public class CartPage extends BaseCommands {

    private final By TEXT_PRODUCT_NAME = By.xpath("//*[class='product__title']");
    private final By TEXT_PRODUCT_UNITS = By.xpath("//*[name='articleCount']");

    private final int numberOfItemsToAddToChart = Integer.parseInt(PropertyManager.getInstance().getNumberOfItemsToPurchase());

    HashMap<Integer, String> productNameAndUnitsInCart = new HashMap<>();
    ExtentTest extentTest = ExtentReportManager.getExtentTest();
    ProductPage productPage = BaseTest.getHomePage();


    public void userVerifyProductNameUnitsAndTotalPrice() {
        String productName;
        int productUnits;

        for(int i = 0; i<numberOfItemsToAddToChart;i++ ){
            productName= getText(findElementIndex(TEXT_PRODUCT_NAME,i));
            productUnits = Integer.parseInt(getValue(findElementIndex(TEXT_PRODUCT_UNITS,i)));
            productNameAndUnitsInCart.put(productUnits,productName);
        }
        Assert.assertEquals(productPage.productNameAndUnitsAdded, productNameAndUnitsInCart);
    }
}

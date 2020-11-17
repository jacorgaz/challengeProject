package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import seleniumSupport.BaseCommands;
import tests.BaseTestController;
import utils.*;

import java.io.IOException;
import java.util.HashMap;


public class CartPage extends BaseCommands {
    /*
     * Page Elements
     */

    private final By TEXT_PRODUCT_NAME = By.xpath("//*[@data-zta='productName']");
    
    HashMap<String , Integer> productNameAndUnitsInCart = new HashMap<>();
    int numberOfItemsToAddToCart;


    /**
     * This method is used to retrieve the number of items of each product displayed in shopping cart for later comparison,
     *  with the total units added in page product screen.
     * @param index Positions of the element in table product
     * @return String with the units value per product
     */

    private String findUnitsAddedToProduct(int index){
        final By TABLE_PRODUCTS = By.xpath("//*[@class='cart__table__row']");
        final By PRODUCT_CHILD_FIRST_DESCENDANT = By.xpath("//*[@class='quantity regular__product']");
        final By PRODUCT_CHILD_SECOND_DESCENDANT = By.xpath("//*[@class='articleQtyForm']");
        final By PRODUCT_CHILD_THIRD_DESCENDANT = By.xpath("//*[@class='v3-counter']");

        return DriverManager.getDriver().findElements(TABLE_PRODUCTS)
                .get(index).findElements(PRODUCT_CHILD_FIRST_DESCENDANT)
                .get(index).findElements(PRODUCT_CHILD_SECOND_DESCENDANT)
                .get(index).findElements(PRODUCT_CHILD_THIRD_DESCENDANT)
                .get(index).findElements(By.tagName("input")).get(0)
                .getAttribute("value");
    }

    /**
     * This method is used to verify that product name and units selected in product page,
        are the same displayed on cart screen.
     */
    public void userVerifyProductNameUnitsAndTotalPrice() throws IOException {
        numberOfItemsToAddToCart =  Integer.parseInt(PropertyManager.getInstance().getNumberOfItemsToPurchase());
        int productUnits;
        String productName;
        ProductPage productPage = BaseTestController.getHomePage();
        ExtentTest extentTest = ExtentReportManager.getExtentTest();

        for(int i = 0; i< numberOfItemsToAddToCart; i++ ){
            productName= getText(findElementIndex(TEXT_PRODUCT_NAME,i));
            productUnits = Integer.parseInt(findUnitsAddedToProduct(i));
            productNameAndUnitsInCart.put(productName,productUnits);
            extentTest.info("Products added " + productName);//Add product name to report
        }

        GetScreenShot.capture("Products added " + DriverManager.getBrowserName());//Add screenshot to report
        Assert.assertEquals(productPage.productNameAndUnitsAddedToCart, productNameAndUnitsInCart);
    }
}

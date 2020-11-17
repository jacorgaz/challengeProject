package tests;

import org.testng.annotations.Test;
import utils.Retry;
import java.io.IOException;

public class RegressionTests extends BaseTestController {

    @Test(description = "Check that a customer can add an article or articles to the cart",
            retryAnalyzer = Retry.class)
    public void testAddProductsToCart() throws IOException {
        homePage.userCloseCookiesBanner();
        homePage.userAddProductsToShoppingCart();
        homePage.userVerifyInBubbleCartNumberOfUnitsAdded();
        homePage.useGoToCart();
        cartPage.userVerifyProductNameUnitsAndTotalPrice();
    }

    @Test(description = "check that there is a tutorial inside and it's showing information",
            retryAnalyzer = Retry.class)
    public void testVerifyInfoInProgramPage() {
        pointsProgramPage.userVerifyProgramTutorialIsDisplayed();
    }

}

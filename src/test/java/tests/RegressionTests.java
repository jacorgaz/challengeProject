package tests;

import org.testng.annotations.Test;
import utils.Retry;

import java.io.IOException;

public class RegressionTests extends  BaseTest{
    @Test(retryAnalyzer = Retry.class)
    public void test_AddProductsToCart() throws IOException {
        homePage.userCloseCookiesBanner();
        homePage.userAddProductsToShoppingCart();
        homePage.userVerifyInBubbleCartNumberOfUnitsAdded();
        homePage.useGoToCart();
        cartPage.userVerifyProductNameUnitsAndTotalPrice();
    }
}

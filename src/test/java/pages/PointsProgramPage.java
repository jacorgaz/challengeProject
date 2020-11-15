package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import tests.BaseTest;
import utils.DriverManager;
import utils.ExtentReportManager;
import utils.GetScreenShot;
import utils.PropertyManager;

import java.io.IOException;
import java.util.HashMap;


public class PointsProgramPage extends BaseCommands {
    /*
     * Page Elements
     */

    private final By TEXT_TUTORIAL_HEADER = By.xpath("//*[@class='ui-h3 ui-text--primary']");
    private final By TEXT_NUMBER_STEP = By.xpath("//*[@class='loy-bppo-benefitsContainer__roundNumber']");
    private final By IMAGE_STEP= By.xpath("//*[@class='loy-bppo-benefitsContainer__diagramImg']");
    private final By GRID_TUTORIAL = By.xpath("//*[@data-ui-grid='12 4@md']");


    ExtentTest extentTest = ExtentReportManager.getExtentTest();
    String imageText;
    String stepDescription;

    private void verifyStepOneInfoIsDisplayed(){
        imageText= getText(findElementIndex(GRID_TUTORIAL,0).findElement(By.tagName("h5")));
        stepDescription= getText(findElementIndex(GRID_TUTORIAL,0).findElement(By.tagName("p")));
        Assert.assertTrue(getText(findElementIndex(TEXT_NUMBER_STEP,0)).equalsIgnoreCase("1"));
        Assert.assertTrue(isDisplayed(IMAGE_STEP));
        Assert.assertEquals(imageText, "Collect with each purchase");
        Assert.assertEquals(stepDescription, "It's FREE! Registered customers automatically earn zooPoints on every purchase.");
    }

    private void verifyStepTwoInfoIsDisplayed(){
        imageText= getText(findElementIndex(GRID_TUTORIAL,1).findElement(By.tagName("h5")));
        stepDescription= getText(findElementIndex(GRID_TUTORIAL,1).findElement(By.tagName("p")));
        Assert.assertTrue(getText(findElementIndex(TEXT_NUMBER_STEP,1)).equalsIgnoreCase("2"));
        Assert.assertTrue(isDisplayed(IMAGE_STEP));
        Assert.assertEquals(imageText, "They soon add up");
        Assert.assertEquals(stepDescription, "Easy calculation €1.00 = 1 zooPoint. zooPoints are credited after order has been paid.");
    }

    private void verifyStepThreeInfoIsDisplayed(){
        imageText= getText(findElementIndex(GRID_TUTORIAL,2).findElement(By.tagName("h5")));
        stepDescription= getText(findElementIndex(GRID_TUTORIAL,2).findElement(By.tagName("p")));
        Assert.assertTrue(getText(findElementIndex(TEXT_NUMBER_STEP,2)).equalsIgnoreCase("3"));
        Assert.assertTrue(isDisplayed(IMAGE_STEP));
        Assert.assertEquals(imageText, "Redeem your zooPoints");
        Assert.assertEquals(stepDescription, "Choose your free zooPoints Reward in our Rewards Shop and add it to your order.");
    }


    /**
     * This method is used to verify that in tutorial are displayed,
     * header description,
     * number step,
     * image,
     * image description and step description.
     */
    public void userVerifyProgramTutorialIsDisplayed() {
        DriverManager.getDriver().get("https://www.zooplus.com/bonuspoints/overview");
        Assert.assertEquals(getText(TEXT_TUTORIAL_HEADER), "How zooPoints work");
        verifyStepOneInfoIsDisplayed();
        verifyStepTwoInfoIsDisplayed();
        verifyStepThreeInfoIsDisplayed();
    }
}

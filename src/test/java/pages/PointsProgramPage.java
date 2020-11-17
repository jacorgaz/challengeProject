package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.*;

import static utils.Constants.PROGRAM_POINTS_IMAGE_TEXT;
import static utils.Constants.PROGRAM_POINTS_STEP_DESCRIPTION;


public class PointsProgramPage extends BaseCommands {
    /*
     * Page Elements
     */

    private final By TEXT_TUTORIAL_HEADER = By.xpath("//*[@class='ui-h3 ui-text--primary']");
    private final By TEXT_NUMBER_STEP = By.xpath("//*[@class='loy-bppo-benefitsContainer__roundNumber']");
    private final By IMAGE_STEP= By.xpath("//*[@class='loy-bppo-benefitsContainer__diagramImg']");
    private final By GRID_TUTORIAL = By.xpath("//*[@data-ui-grid='12 4@md']");
    private final By TAG_IMAGE_TEXT = By.tagName("h5");
    private final By TAG_STEP_DESCRIPTION = By.tagName("p");

    private String imageText;
    private String stepDescription;
    private String stepNumber;
    private String stepNumberExpected;

    private void verifyStepOneInfoIsDisplayed(){
        imageText= getText(findElementIndex(GRID_TUTORIAL,0).findElement(TAG_IMAGE_TEXT));
        stepDescription= getText(findElementIndex(GRID_TUTORIAL,0).findElement(TAG_STEP_DESCRIPTION));
        stepNumber=getText(findElementIndex(TEXT_NUMBER_STEP,0));
        stepNumberExpected ="1";
        Assert.assertTrue(stepNumber.equalsIgnoreCase(stepNumberExpected));
        Assert.assertTrue(isDisplayed(IMAGE_STEP));
        Assert.assertTrue(PROGRAM_POINTS_IMAGE_TEXT.contains(imageText));
        Assert.assertTrue(PROGRAM_POINTS_STEP_DESCRIPTION.contains(stepDescription));
    }

    private void verifyStepTwoInfoIsDisplayed(){
        imageText= getText(findElementIndex(GRID_TUTORIAL,1).findElement(TAG_IMAGE_TEXT));
        stepDescription= getText(findElementIndex(GRID_TUTORIAL,1).findElement(TAG_STEP_DESCRIPTION));
        stepNumber=getText(findElementIndex(TEXT_NUMBER_STEP,1));
        stepNumberExpected ="2";
        Assert.assertTrue(stepNumber.equalsIgnoreCase(stepNumberExpected));
        Assert.assertTrue(isDisplayed(IMAGE_STEP));
        Assert.assertTrue(PROGRAM_POINTS_IMAGE_TEXT.contains(imageText));
        Assert.assertTrue(PROGRAM_POINTS_STEP_DESCRIPTION.contains(stepDescription));
    }

    private void verifyStepThreeInfoIsDisplayed(){
        imageText= getText(findElementIndex(GRID_TUTORIAL,2).findElement(TAG_IMAGE_TEXT));
        stepDescription= getText(findElementIndex(GRID_TUTORIAL,2).findElement(TAG_STEP_DESCRIPTION));
        stepNumber=getText(findElementIndex(TEXT_NUMBER_STEP,2));
        stepNumberExpected ="3";
        Assert.assertTrue(stepNumber.equalsIgnoreCase(stepNumberExpected));
        Assert.assertTrue(isDisplayed(IMAGE_STEP));
        Assert.assertTrue(PROGRAM_POINTS_IMAGE_TEXT.contains(imageText));
        Assert.assertTrue(PROGRAM_POINTS_STEP_DESCRIPTION.contains(stepDescription));
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

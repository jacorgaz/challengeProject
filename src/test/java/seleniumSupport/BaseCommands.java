package seleniumSupport;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import utils.Constants;
import utils.DriverManager;
import utils.ExtentReportManager;

public class BaseCommands {

    private final WebDriver DRIVER = DriverManager.getDriver();
    private  ExtentTest EXTENT_TEST = ExtentReportManager.getExtentTest();

    public void navigateBack() {
        DRIVER.navigate().back();
    }

    public void goToURL(String url) {
        DRIVER.get(url);
    }

    public boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        } catch (TimeoutException e) {
            EXTENT_TEST.error("Element identified by " + locator.toString() + " was not not found");
        }
        return false;
    }

    public WebElement findElementIndex(By locator, int index) {
        (new WebDriverWait(DRIVER, 10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return DRIVER.findElements(locator).get(index);
    }

    public String getText(By locator) {
        try {
            new WebDriverWait(DRIVER, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
            return DRIVER.findElement(locator).getText();
        } catch (TimeoutException e) {
            EXTENT_TEST.error("Element identified by " + locator.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public String getText(WebElement locator) {
        try {
            return locator.getText();
        } catch (TimeoutException e) {
            EXTENT_TEST.error("Element identified by " + locator.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public String getValue(By locator) {
        try {
            (new WebDriverWait(DRIVER, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
            return DRIVER.findElement(locator).getAttribute("value");
        } catch (TimeoutException e) {
            EXTENT_TEST.error("Element identified by " + locator.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public WebElement find(By locator) {
        try {
            new WebDriverWait(DRIVER, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
            return DRIVER.findElement(locator);
        } catch (TimeoutException e) {
            EXTENT_TEST.error("Element identified by " + locator.toString() + " was not found after 20 seconds");
        }
        return null;
    }

    public void clickElement(By locator) {
        try {
            (new WebDriverWait(DRIVER, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
            DRIVER.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            DRIVER.findElement(locator).click();
        } catch (TimeoutException e) {
            EXTENT_TEST.info("Element identified by " + locator.toString() + " was not clickable after 10 seconds");
        }
    }

    public void clickElementByIndex(By locator, int index) {
        try {
            (new WebDriverWait(DRIVER, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
            DRIVER.findElements(locator).get(index).click();
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            DRIVER.findElement(locator).click();
        } catch (TimeoutException e) {
            EXTENT_TEST.info("Element identified by " + locator.toString() + " was not clickable after 10 seconds");
        }
    }

    public static String getSelenoidVideos(){
        RemoteWebDriver driver = DriverManager.getDriver();
        String result;
        String path= Constants.SELENOID_DOCKER_VIDEO +driver.getSessionId()+".mp4";
        result =  "<video width=\"360\" height=\"640\" controls>\n" +
                "<source src="+path+" type=\"video/mp4\">\n" +
                "</video>";
        return result;
    }
}

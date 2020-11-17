package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import utils.Constants;
import utils.DriverManager;
import utils.ExtentReportManager;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseCommands {

    private  WebDriver driver = DriverManager.getDriver();

    private  ExtentTest extentTest = ExtentReportManager.getExtentTest();

    public void navigateBack() {
        driver.navigate().back();
    }

    public boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not not found");
        }
        return false;
    }

    public WebElement findElementIndex(By locator, int index) {
        return driver.findElements(locator).get(index);
    }

    public String getText(By by) {
        try {
            return driver.findElement(by).getText();
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + by.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public String getText(WebElement locator) {
        try {
            return locator.getText();
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public String getValue(By locator) {
        try {
            return driver.findElement(locator).getAttribute("value");
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void clickElement(By locator) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
            driver.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            driver.findElement(locator).click();
        } catch (TimeoutException e) {
            extentTest.info("Element identified by " + locator.toString() + " was not clickable after 10 seconds");
        }
    }

    public void clickElementByIndex(By locator, int index) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
            driver.findElements(locator).get(index).click();
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            driver.findElement(locator).click();
        } catch (TimeoutException e) {
            extentTest.info("Element identified by " + locator.toString() + " was not clickable after 10 seconds");
        }
    }

    public static String getSelenoidVideos(){
        RemoteWebDriver driver = DriverManager.getDriver();
        String result;
        String path= Constants.SELENOID_DOCKER_VIDEO +"/video/"+driver.getSessionId()+".mp4";
        result =  "<video width=\"360\" height=\"640\" controls>\n" +
                "<source src="+path+" type=\"video/mp4\">\n" +
                "</video>";
        return result;
    }
}

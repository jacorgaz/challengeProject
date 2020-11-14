package pages;

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

    public static final String testDataExcelFileName = "testData.xlsx";

    public void navigateBack() {
        driver.navigate().back();
    }
    public void waitForElementDisplayed(By by, int timeInterval) {
        WebDriver driver = DriverManager.getDriver();
        try {
                new WebDriverWait(driver, timeInterval).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (org.openqa.selenium.TimeoutException exception) {
            extentTest.log(Status.INFO, "Element identified by " + by.toString() + " was not clickable after " + timeInterval);
        }
    }

    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public void waitFluentElement(By element, String text) {
        Wait wait = new FluentWait <WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.textToBe(element,text));
    }

    public void waitFluentElement(By element) {
        Wait wait = new FluentWait <WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }


    public void uploadFile(By by, String filePath) {
        WebElement uploadElement = driver.findElement(by);
        // enter the file path onto the file-selection input field
        uploadElement.sendKeys(filePath);
    }

    public boolean isElementDisplayed(By by, int timeInterval) {
        try {
            new WebDriverWait(driver, timeInterval).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    public boolean isElementSelected(By element) {
        try {
            return driver.findElement(element).isSelected();
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
    }

    public boolean elementExist(By by) {
        try {
            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public By element(By element) {
        try {
            return element;
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + element.toString() + " was not not found");
        }
        return null;
    }

    public List<WebElement> findElements(By locatorValue) {
        try {
            return driver.findElements(locatorValue);
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + locatorValue.toString() + " was not not found");
        }
        return null;
    }

    public By findElementLocatorContains(String locatorValue, String locatorContains) {
        String element = "//*[contains(@" + locatorValue + ",'" + locatorContains + "')]";
        try {
            return By.xpath(element);
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + By.xpath(element).toString() + " was not not found");
        }
        return null;
    }

    public By findElementByIndex(List<By> locatorValue, int index) {
        try {
            return locatorValue.get(index);
        } catch (NoSuchElementException e) {
        }
        return null;
    }


    public By elementContainText(String locatorValue, String locatorName, String textContains) {
        String element = "//*[@" + locatorValue + "='" + locatorName + "'" + "]" + "[contains(text()," + "'" + textContains + "'" + ")]";
        try {
            return By.xpath(element);
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + By.xpath(element).toString() + " was not not found");
        }
        return null;
    }

    public By elementContainText(String textContains) {
        String element = "//*[contains(text()," + "'" + textContains + "'" + ")]";
        try {
            return By.xpath(element);
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + By.xpath(element).toString() + " was not not found");
        }
        return null;
    }

    public By link(String typeTag, String xpath) {
        String button = "//*[@" + typeTag + "='" + xpath + "']";
        try {
            return By.xpath(button);
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + By.xpath(button).toString() + " was not not found");
        }
        return null;
    }

    public boolean isEnabled(By locator) {
        if(driver.findElement(locator).isEnabled()){
            return true;
        }else {
            return false;
        }
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

    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void dragAndDrop(By elementToDrag, By elementToDrop) throws InterruptedException {
        Thread.sleep(500);
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        WebElement drag = driver.findElement(elementToDrag);
        WebElement drop = driver.findElement(elementToDrop);
        Actions builder = new Actions(driver);
        builder.dragAndDrop(drag, drop).perform();
        builder.build();
    }

    public WebElement findElementIndex(By locator, int index) {
        return driver.findElements(locator).get(index);
    }




    public WebElement findElementIndex(List<WebElement> locator, int index) {
        return locator.get(index);
    }

    public int findElementSize(By locator) {
        return driver.findElements(locator).size();
    }

    public boolean findElementInPage(String text) {
        try {
            driver.getPageSource().contains(text);
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
        } catch (TimeoutException e) {
            extentTest.info("Texts " + text + " was not clickable after 10 seconds");
        }
        return driver.getPageSource().contains(text);
    }

    public List<WebElement> findElementsChild(By locator, By childClassName) {
        return driver.findElement(locator).findElements(childClassName);
    }

    public void changeToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void changeToFrame(int frame) {
        driver.switchTo().frame(frame);
    }

    public void changeToMainFrame() {
        driver.switchTo().defaultContent();
    }

    public String getAttribute(By by, String attribute) {
        try {
            return driver.findElement(by).getAttribute(attribute);
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + by.toString() + " was not found after 20 seconds");
        }
        return "";
    }

    public String getAttribute(WebElement element, String attribute) {
        try {
            return element.getAttribute(attribute);
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + element.toString() + " was not found after 20 seconds");
        }
        return "";
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

    public String getValue(WebElement locator) {
        try {
            return locator.getAttribute("value");
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

    public void clickJavaScript(By locator) {
        WebElement elementToClick = driver.findElement(locator);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", elementToClick);
    }

    public void selectCheckBox(By checkBox, boolean select) {
        if(select) {
            if (!isElementSelected(checkBox)) ;
            clickElement(checkBox);
        }
        if(!select){
            if (isElementSelected(checkBox));
            clickElement(checkBox);
        }
    }

    public void enterText(By locator, String text) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).sendKeys(text);
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            driver.findElement(locator).click();
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 10 seconds");
        }
    }

    public void enterText(WebElement locator, String text) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
            locator.sendKeys(text);
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            locator.click();
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 10 seconds");
        }
    }

    public boolean isTextFieldCleared(By locator) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
            if(driver.findElement(locator).getAttribute("value").isEmpty()){
                return true;

            }else {
                return false;
            }
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 10 seconds");
        }
        return false;
    }

    public boolean isTextFieldCleared(WebElement locator) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
            if(locator.getAttribute("value").isEmpty()){
                return true;

            }else {
                return false;
            }
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 10 seconds");
        }
        return false;
    }

    public void clearText(By locator) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).clear();
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            driver.findElement(locator).clear();
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 10 seconds");
        }
    }


    public void clearTextCharacterByCharacter(By locator) {
        do {
            driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
        }while (!isTextFieldCleared(locator));
    }

    public void clearTextCharacterByCharacter(WebElement locator) {
        do {
            locator.sendKeys(Keys.BACK_SPACE);
        }while (!isTextFieldCleared(locator));
    }

    public void clearText(WebElement locator) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
            locator.clear();
        } catch (StaleElementReferenceException e) {
            // simply retry finding the element in the refreshed DOM
            locator.clear();
        } catch (TimeoutException e) {
            extentTest.error("Element identified by " + locator.toString() + " was not found after 10 seconds");
        }
    }

    public void selectDropDown(By locator, String text) {
        Select drpCountry = new Select(driver.findElement(locator));
        drpCountry.selectByVisibleText(text);
    }

    public void selectDropDown(String xpath, String text) {
        String dropDown = "//select[@name='" + xpath + "']";
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropDown)));
            Select drpCountry = new Select(driver.findElement(By.xpath(dropDown)));
            drpCountry.selectByVisibleText(text);
        } catch (NoSuchElementException e) {
            extentTest.error("Element identified by " + By.xpath(dropDown).toString() + " was not not found");
        }
    }

    public boolean verifyElementIsDisplayedOnPage(ArrayList<String> test) {
        boolean elementExist = false;
        for (String element : test) {
            if (driver.getPageSource().contains(element)) {
                elementExist = true;
            } else {
                return elementExist = false;
            }
        }
        return elementExist;
    }



    public boolean isFileDownloadedAndDeleted(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                return true;
            }
        }
        return false;
    }


    public void scrollToBottom(){
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void goToURL(String url){
        driver.navigate().to(url);
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

    public ResultSet getDataBaseQueryResult(String query) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://sanitas-poc.mo2o.com:3306", "qapruebas", "Luchana23");
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        return null;
    }

    public void updateDataBase(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://sanitas-poc.mo2o.com:3306", "qapruebas", "Luchana23");
        Statement stmt = con.createStatement();
        boolean result = stmt.execute(query);
    }

    public static String getCurrentTimeInNanoSeconds() throws ClassNotFoundException, SQLException {
        Instant instant = Instant.now();
        return String.valueOf(instant.getNano());
    }
}

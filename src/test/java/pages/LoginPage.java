package pages;

import data.DataModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.DriverManager;

public class LoginPage extends BaseCommands {

    private By textFieldUserName = By.id("txtName");
    private By textFieldPass = By.id("txtPass");
    private By buttonSubmit = By.xpath("//button[@name='login']");
    private By staticTextWrongCredentials = By.xpath("//*[@class='error-bl']");
    private String wrongCredentialsText = "El usuario o la contraseñan no coinciden";
    private WebDriver driver =  DriverManager.getDriver();

    private void login(DataModel.LoginTestData userData ) throws InterruptedException {
        enterText(textFieldUserName, userData.userName);
        enterText(textFieldPass, userData.userPassword);
        clickElement(buttonSubmit);
        /** No idea but after clicking on Submit teh text boxes  are automatically cleared */
        if(isElementDisplayed(textFieldUserName,3)){
            if(isTextFieldCleared(textFieldUserName)){
                enterText(textFieldUserName, userData.userName);
                enterText(textFieldPass, userData.userPassword);
                clickElement(buttonSubmit);
            }
        }
    }

    public void userClearTextFields(){
        clearText(textFieldUserName);
        clearText(textFieldPass);
    }

    public void userVerifyWrongCredentials(DataModel.LoginTestData userData ) throws InterruptedException {
        login(userData);
        Assert.assertTrue(getText(staticTextWrongCredentials).equalsIgnoreCase(wrongCredentialsText));
    }

    public void userLogin(DataModel.LoginTestData userData ) throws InterruptedException {
        login(userData);
    }

    public LoginPage userVerifySubmitButtonIsEnabled() throws InterruptedException {
        driver.navigate().refresh();
        Assert.assertFalse(isEnabled(buttonSubmit));
        return this;
    }
}
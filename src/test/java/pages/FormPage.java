package pages;

import com.aventstack.extentreports.ExtentTest;
import data.DataModel;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.ExtentReportManager;

public class FormPage extends BaseCommands {
    private ExtentTest extentTest = ExtentReportManager.getExtentTest();
    private By textFieldUser = By.id("username");
    private By textFieldPass = By.id("password");
    private By buttonLogIn = By.className("fa-sign-in");
    private By staticTextLoginConfirmation = By.id("flash");


    public void logIn(String username, String pass){
        enterText(textFieldUser, username);
        enterText(textFieldPass, username);
        clickElement(buttonLogIn);
    }

    public void userVerifyLogIn(String username, String pass){
        logIn(username,pass);
        waitFluentElement(staticTextLoginConfirmation);
        Assert.assertTrue(getText(staticTextLoginConfirmation).equalsIgnoreCase("You logged into a secure area!"));
    }

    public void userVerifyLogIn(DataModel.LoginTestData userData){
        logIn(userData.userName,userData.userPassword);
        waitFluentElement(staticTextLoginConfirmation);
        extentTest.info("<b><u>Credentials info info:</u> </b><br>"+ userData.toString());
        Assert.assertTrue(getText(staticTextLoginConfirmation).equalsIgnoreCase("You logged into a secure area!"));
    }
}

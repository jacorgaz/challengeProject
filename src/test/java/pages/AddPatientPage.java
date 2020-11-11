package pages;

import com.aventstack.extentreports.ExtentTest;
import data.DataModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.DriverManager;
import utils.ExtentReportManager;
import java.io.IOException;
import java.util.*;


public class AddPatientPage extends BaseCommands {
    private ExtentTest extentReport = ExtentReportManager.getExtentTest();
    private WebDriver page = DriverManager.getDriver();

    private By textFieldParentsName = By.id("txtName");
    private By textFieldBabyName = By.id("txtBaby");
    private By textFieldEmail = By.id("txtMail");
    private By buttonNext = By.xpath("//button[@name='login']");
    private By staticTextErrorParentsName = By.xpath("//*[@class='error-bl']");
    private By staticTextInfoConfirmPage = By.xpath("//p");
    private By staticTextParentsAndChild = By.xpath("//td");
    private By buttonModifyData = By.id("btnChange");
    private By buttonBackToCameraScreen = By.className("form-back");

    public ArrayList<String> getDataInformedInFormulary() {
        return dataInformedInFormulary;
    }

    public void setDataInformedInFormulary(ArrayList<String> dataInformedInFormulary) {
        this.dataInformedInFormulary = dataInformedInFormulary;
    }

    ArrayList<String> dataInformedInFormulary = new ArrayList<>();;

    private void informFormularyData(String parentsName, String childName, String email) throws IOException, InterruptedException {
        enterText(textFieldParentsName, parentsName);
        enterText(textFieldBabyName, childName);
        enterText(textFieldEmail, email);
    }

    private void clearTextFields() {
        List<WebElement> textFieldsToClear = findElements(By.xpath("//input"));
        for (WebElement element : textFieldsToClear) {
            element.clear();
        }
    }

    private void verifyAllFieldsButtonsAreDisplayed() {
        ArrayList<By> mandatoryObjects = new ArrayList<By>(Arrays.asList(textFieldParentsName, textFieldBabyName, textFieldEmail, buttonNext));
        for (By element : mandatoryObjects) {
            Assert.assertTrue(isDisplayed(element));
        }
    }

    private void verifyFormularyDataValidation() {
        waitForElementDisplayed(staticTextErrorParentsName,10);
        String error= "Indique al menos un apellido del padre, madre o tutor";
        Assert.assertTrue(getText(staticTextErrorParentsName).equalsIgnoreCase(error));
    }

    private void verifyTextFieldsInfoFilledIn(ArrayList<String> dataInformedInFormulary){
        List<WebElement> textFields= findElements(By.xpath("//input"));
        int index = 0;
        for (String entry : dataInformedInFormulary) {
            Assert.assertTrue(getValue(findElementIndex(textFields, index)).equalsIgnoreCase(entry));
            index++;
        }
    }

    public void userVerifyFormularyMandatoryData(DataModel.AddParentsPageData data) throws IOException, InterruptedException {
        informFormularyData(data.parentsName, data.childName, data.email);
        verifyAllFieldsButtonsAreDisplayed();
        Assert.assertFalse(isEnabled(buttonNext));;
        page.navigate().refresh();
    }
    public void userVerifyFomularyDataValidation(DataModel.AddParentsPageData data) throws IOException, InterruptedException {
        page.navigate().refresh();
        informFormularyData(data.parentsName, data.childName, data.email);
        click(buttonNext);
        verifyFormularyDataValidation();
    }

    public void userInformFormularyData(DataModel.AddParentsPageData data) throws IOException, InterruptedException {
        page.navigate().refresh();
        ArrayList<String> dataInformedInFormulary =  new ArrayList<>();
        dataInformedInFormulary.add(data.parentsName);
        dataInformedInFormulary.add(data.childName);
        dataInformedInFormulary.add(data.email);
        informFormularyData(data.parentsName,data.childName,data.email);
        setDataInformedInFormulary(dataInformedInFormulary);
        click(buttonNext);
    }

    public void userVerifyConfirmPageData() {
        String headerDesc = "Revisa que los datos son correctos antes de confirmar. Se enviará un código de acceso a la cámara del bebé al e-mail que hayas escrito.";
        String parentsName = getText(findElementIndex(staticTextParentsAndChild,0));
        String email= getText(findElementIndex(staticTextParentsAndChild,1));

        Assert.assertTrue(getText(staticTextInfoConfirmPage)
                .equalsIgnoreCase(headerDesc));
        Assert.assertTrue(getDataInformedInFormulary().contains(parentsName)&&getDataInformedInFormulary().contains(email));
    }

    public void userGoBackToFormularyScreen() {
       click(buttonModifyData);
    }

    public void userGoBackToCamerasPage() {
        click(buttonBackToCameraScreen);
    }

    public void userVerifyFormularyFilledInInfo() {
       verifyTextFieldsInfoFilledIn(getDataInformedInFormulary());
    }
}

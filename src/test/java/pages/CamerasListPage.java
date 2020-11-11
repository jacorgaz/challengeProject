package pages;

import com.aventstack.extentreports.ExtentTest;
import data.DataModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.DriverManager;
import utils.ExtentReportManager;
import utils.GetScreenShot;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CamerasListPage extends BaseCommands {

    private ExtentTest extentReport = ExtentReportManager.getExtentTest();
    private WebDriver page = DriverManager.getDriver();

    private By staticTextCameraNumber = By.xpath("//*[contains(@class, 'subtitle')]");
    private By textFieldsRoomNumber = By.xpath("//*[contains(@id, 'mat-input')]");
    private By buttonCamera = By.xpath("//*[@class='watch-camera']");
    private By buttonAccept = By.xpath("//*[@class='form-btn4']");
    private By buttonSave = By.xpath("//*[@name='login']");
    private By buttonLogout = By.id("btnClose");
    private By buttonAssign = By.xpath("//*[@name='login']");

    private ArrayList<String> arrayTextFieldsValueAfterSaving = new ArrayList<>();
    private ArrayList<String> arrayTextFieldsValueAfterLogin = new ArrayList<>();
    private ArrayList<String> arrayRoomNumbersInDDBB = new ArrayList<>();
    private ArrayList<String> arrayRoomNumbersInFront = new ArrayList<>();
    private ArrayList<String> arrayTextFieldsValueBeforeSaving = new ArrayList<>();
    private ArrayList<String> arrayIdNumberOfHospitalsInBBDD = new ArrayList<>();
    private String idHospitalOfUserBeforeChangeInBBDD;

    public ArrayList<String> getArrayRoomNumbersInDDBB() {
        return arrayRoomNumbersInDDBB;
    }
    public void setArrayRoomNumbersInDDBB(ArrayList<String> arrayRoomNumbersInDDBB) {
        if(!this.arrayRoomNumbersInDDBB.isEmpty()){
            this.arrayTextFieldsValueAfterSaving.clear();
        }
        this.arrayRoomNumbersInDDBB = arrayRoomNumbersInDDBB;
    }

    public String getIdHospitalOfUserBeforeChangeInBBDD() {
        return idHospitalOfUserBeforeChangeInBBDD;
    }
    public void setIdHospitalOfUserBeforeChangeInBBDD(String idHospitalOfUserBeforeChangeInBBDD) {
        this.idHospitalOfUserBeforeChangeInBBDD = idHospitalOfUserBeforeChangeInBBDD;
    }

    public ArrayList<String> getArrayIdNumberOfHospitalsInBBDD() {
        return arrayIdNumberOfHospitalsInBBDD;
    }
    public void setArrayIdNumberOfHospitalsInBBDD(ArrayList<String> hospitalNumber) {
        if(!this.arrayIdNumberOfHospitalsInBBDD.isEmpty()){
            this.arrayIdNumberOfHospitalsInBBDD.clear();
        }
        this.arrayIdNumberOfHospitalsInBBDD=hospitalNumber;
    }

    public ArrayList<String> getArrayTextFieldsValueAfterSaving() {
        return arrayTextFieldsValueAfterSaving;
    }
    public void setArrayTextFieldsValueAfterSaving(ArrayList arrayTextFieldsValueAfterSaving) {
        if(!this.arrayTextFieldsValueAfterSaving.isEmpty()){
            this.arrayTextFieldsValueAfterSaving.clear();
        }
        this.arrayTextFieldsValueAfterSaving=arrayTextFieldsValueAfterSaving;
    }

    public ArrayList<String> getArrayTextFieldsValueAfterLogin() {
        return arrayTextFieldsValueAfterLogin;
    }
    public void setArrayTextFieldsValueAfterLogin(ArrayList arrayTextFieldsValueAfterLogin) {
        if(!this.arrayTextFieldsValueAfterLogin.isEmpty()){
            this.arrayTextFieldsValueAfterLogin.clear();
        }
        this.arrayTextFieldsValueAfterLogin=arrayTextFieldsValueAfterLogin;
    }

    public ArrayList<String> getArrayTextFieldsValueBeforeSaving() {
        return arrayTextFieldsValueBeforeSaving;
    }
    public void setArrayTextFieldsValueBeforeSaving(ArrayList arrayTextFieldsValueBeforeSaving) {
        if(!this.arrayTextFieldsValueBeforeSaving.isEmpty()){
            this.arrayTextFieldsValueBeforeSaving.clear();
        }
        this.arrayTextFieldsValueBeforeSaving= arrayTextFieldsValueBeforeSaving;
    }

    public ArrayList<String> getArrayRoomNumbersInFront() {
        return arrayRoomNumbersInFront;
    }
    public void setArrayRoomNumbersInFront(ArrayList<String> arrayRoomNumbersInFront) {
        this.arrayRoomNumbersInFront = arrayRoomNumbersInFront;
    }

    /**
     * This method is to retrieve the number of room number text fields depending on the number of Cameras displayed
     * @return Array with all textfields found
     */
    private int getNumberOfTextFieldsRoomNumberAreDisplayed() {
        ArrayList<WebElement>  textFieldsRoomNumber= new ArrayList<WebElement>();
        int numberOfCameras = findElementSize(staticTextCameraNumber);
        for(int i=0; i < numberOfCameras; i++){
            textFieldsRoomNumber.add(findElementIndex(this.textFieldsRoomNumber,i));
        }
        return textFieldsRoomNumber.size();
    }

    private List <WebElement> getNumberAllTextFieldsDisplayed() {
        ArrayList<WebElement>  textFieldsRoomNumber= new ArrayList<WebElement>();
        int numberOfCameras = findElementSize(staticTextCameraNumber);
        for(int i=0; i < numberOfCameras; i++){
            textFieldsRoomNumber.add(findElementIndex(this.textFieldsRoomNumber,i));
        }
        return textFieldsRoomNumber;
    }

    private void modifyAllRoomsNumbers(){
        enterTextAndSaveInAllRoomNumber();
        setArrayTextFieldsValueAfterSaving(getValueOfAllTextFields());
    }

    private void verifyAllRoomsNumbersAfterLogin(){
        getValueOfAllTextFields();
        Assert.assertTrue(arrayTextFieldsValueAfterSaving.equals(arrayTextFieldsValueAfterLogin));
    }

    private ArrayList<WebElement> getNumberOfSaveButtons() {
        ArrayList<WebElement>  buttonSave= new ArrayList<WebElement>();
        int numberOfCameras = findElementSize(staticTextCameraNumber);
        for(int i=0; i < numberOfCameras; i++){
            buttonSave.add(findElementIndex(this.buttonSave,i));
        }
        return buttonSave;
    }

    private void clickSaveButton(int index) {
        findElementIndex(buttonSave,index).click();
        click(buttonAccept);
    }

    public void userLogOut(){
        click(buttonLogout);
    }

    private void enterTextAndSaveInAllRoomNumber(){
        ArrayList <String> elementsList = new ArrayList<>();
        getNumberOfTextFieldsRoomNumberAreDisplayed();
        int roomNumber =(int)(Math.random()*((9999-0)+1))+0;
        WebElement textFieldRoom;
        for(int i = 0; i< getNumberOfTextFieldsRoomNumberAreDisplayed(); i++){
            textFieldRoom = findElementIndex(textFieldsRoomNumber,i);
            clearText(textFieldRoom);
            enterText(textFieldRoom,"Room"+roomNumber+i);
            elementsList.add(textFieldRoom.getAttribute("value"));
            clickSaveButton(i);
        }
        setArrayTextFieldsValueBeforeSaving(elementsList);
    }

    private void enterTextAndSaveInOnlyOneRoomNumber(){
        ArrayList <String> elementsList = new ArrayList<>();
        getNumberOfTextFieldsRoomNumberAreDisplayed();
        int roomNumber =(int)(Math.random()*((9999-0)+1))+0;
        WebElement textFieldRoom;
        for(int i = 0; i< getNumberOfTextFieldsRoomNumberAreDisplayed(); i++){
            textFieldRoom = findElementIndex(textFieldsRoomNumber,i);
            clearText(textFieldRoom);
            enterText(textFieldRoom,"Room"+roomNumber+i);
            elementsList.add("Room"+roomNumber+i);
            clickSaveButton(0);
        }
        setArrayTextFieldsValueBeforeSaving(elementsList);
    }

    private ArrayList<String> getValueOfAllTextFields() {
        ArrayList<String> elementsList = new ArrayList<>();
        for (WebElement element : getNumberAllTextFieldsDisplayed()) {
            elementsList.add(element.getAttribute("value"));
        }
        return elementsList;
    }

    private String getIdIdHospitalFromUserInBBDD(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getDataBaseQueryResult("select idHospital from sanitaswebcamNew.Usuario_has_Hospital where \n" +
                "idUsuario=(select idUsuario from sanitaswebcamNew.Usuario where email =\"" + userName +"\");");
        while (resultSet.next()) {
            setIdHospitalOfUserBeforeChangeInBBDD(resultSet.getString(1));
            return resultSet.getString(1);
        }
        return null;
    }

    private String getIdUserInBBDD(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getDataBaseQueryResult("SELECT IdUsuario FROM sanitaswebcamNew.Usuario where  email =\""+ userName +"\";");
        while (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    private ArrayList<String> getIdHospitalsInBBDD() throws SQLException, ClassNotFoundException {
        ArrayList<String> hospitals = new ArrayList<>();
        ResultSet resultSet= getDataBaseQueryResult("SELECT * FROM sanitaswebcamNew.Hospital;");
        while (resultSet.next())
        {
            hospitals.add(resultSet.getString(1));
        }
        return hospitals;
    }

    private ArrayList<String> selectCameras() throws SQLException, ClassNotFoundException {
        ArrayList<String> hospitals = new ArrayList<>();
        ResultSet resultSet= getDataBaseQueryResult("SELECT * FROM sanitaswebcamNew.Hospital;");
        while (resultSet.next())
        {
            hospitals.add(resultSet.getString(1));
        }
        return hospitals;
    }

    public void userChangeIDHospitalInBBDD(String userEmail) throws SQLException, ClassNotFoundException {
        setArrayIdNumberOfHospitalsInBBDD(getIdHospitalsInBBDD());
        String actualIdHospital= getIdIdHospitalFromUserInBBDD(userEmail);
        for(String hospitalIdToChange : getArrayIdNumberOfHospitalsInBBDD()){
            if(!hospitalIdToChange.equalsIgnoreCase(actualIdHospital)){
                updateDataBase("UPDATE sanitaswebcamNew.Usuario_has_Hospital SET idHospital ="+hospitalIdToChange+" WHERE idUsuario ="+getIdUserInBBDD(userEmail)+";");
                return;
            }
        }
    }

    public void userChangeIDHospitalInBBDDToOriginalIDHospital(String userEmail) throws SQLException, ClassNotFoundException {
        updateDataBase("UPDATE sanitaswebcamNew.Usuario_has_Hospital SET idHospital ="+getIdHospitalOfUserBeforeChangeInBBDD()+" WHERE idUsuario ="+getIdUserInBBDD(userEmail)+";");
    }

    public void userVerifyRoomsAfterChangingHospitalInBBDD(String user) throws SQLException, ClassNotFoundException {
        setArrayRoomNumbersInDDBB(getRoomsNumberInBBDD(user));
        setArrayRoomNumbersInFront(getValueOfAllTextFields());
        Assert.assertTrue(getArrayRoomNumbersInFront().equals(getArrayRoomNumbersInDDBB()));
        userChangeIDHospitalInBBDDToOriginalIDHospital(user);
    }

    private ArrayList<String> getRoomsNumberInBBDD(String nurseEmail) throws SQLException, ClassNotFoundException {
        ArrayList<String> roomsNumbers = new ArrayList<String>();
        ResultSet resultSet= getDataBaseQueryResult("select room from  sanitaswebcamNew.Camara where \n" +
                "idHospital=(select idHospital from sanitaswebcamNew.Usuario_has_Hospital where \n" +
                "idUsuario=(select idUsuario from sanitaswebcamNew.Usuario where email = \""+nurseEmail+"\"));\n");

        while (resultSet.next())
        {
            String roomNumberInBBDD="";
            extentReport.info("Room number en el hospital  " + resultSet.getString(1));
            roomNumberInBBDD = resultSet.getString(1)==null ? "" : resultSet.getString(1);
            roomsNumbers.add(roomNumberInBBDD);
        }
        return roomsNumbers;
    }

    private void accessCamera() throws IOException, InterruptedException {
        for(int i = 0; i < getNumberOfTextFieldsRoomNumberAreDisplayed();i++){
            String cameraNumber = getText(findElementIndex(staticTextCameraNumber,i));
            String url = System.getProperty("base.url");
            String cameraXpath= "//*[@style='background-image: url("+url+"/api/webcam/"+cameraNumber+"?resolution=1024x576);']";
            By camera = By.xpath(cameraXpath);

            findElementIndex(buttonCamera,i).click();
            isDisplayed(camera);
            extentReport.info("Streaming "+cameraNumber, GetScreenShot.capture(cameraNumber));
            page.navigate().back();
        }
    }

    private void accessAssignCamera() throws IOException, InterruptedException {
        if(findElementSize(buttonAssign)>0){
            findElementIndex(buttonAssign,0).click();
        }
    }

    /**
     * This method is used to verify that all cameras numbers corresponds to the nurse and the right hospital.
     */
    public void userVerifyCameraNumbers(String nurseEmail) throws InterruptedException, SQLException, ClassNotFoundException {
        int cameraNumber = 0;
        String cameraName = "Cámara ";
        ArrayList<String> camarasList = new ArrayList<String>();
        ResultSet resultSet= getDataBaseQueryResult("select idCamara from sanitaswebcamNew.Camara where \n" +
                "idHospital=(select idHospital from sanitaswebcamNew.Usuario_has_Hospital where \n" +
                "idUsuario=(select idUsuario from sanitaswebcamNew.Usuario where email = \""+nurseEmail+"\"))");

        while (resultSet.next())
        {
            camarasList.add(resultSet.getString(1));
            extentReport.info("Enfermera tiene cámara  " + resultSet.getString(1));
        }

        for (String roomNumber : camarasList) {
           Assert.assertTrue(findElementIndex(staticTextCameraNumber,cameraNumber).getText().equalsIgnoreCase(cameraName+roomNumber)); ;
           cameraNumber++;
        }
    }

    /**
     * This method is used to verify that all cameras numbers corresponds to the nurse and the right hospital.
     */
    public void userVerifyRoomNumbersInDDBBWithRoomNumberInFront(String userName) throws SQLException, ClassNotFoundException {
        setArrayRoomNumbersInDDBB(getRoomsNumberInBBDD(userName));
        Assert.assertTrue(getArrayTextFieldsValueAfterSaving().equals(getArrayRoomNumbersInDDBB()));
    }

    public void userVerifyTextFieldsFormats() {
        String alphanumericValueToVerify = "123Ab";
        for(WebElement textFieldRoomNumber : getNumberAllTextFieldsDisplayed()){
            clearText(textFieldRoomNumber);
            enterText(textFieldRoomNumber, alphanumericValueToVerify);
            getText(textFieldRoomNumber).equalsIgnoreCase(alphanumericValueToVerify);
        }
    }

    public void userVerifyUnassignedConditionBeforeLogout(DataModel.LoginTestData userData) throws InterruptedException {
        for(int i = 0; i< getNumberOfTextFieldsRoomNumberAreDisplayed(); i++){
            clearTextCharacterByCharacter(findElementIndex(textFieldsRoomNumber,i));
            click(buttonSave);
            click(buttonAccept);
            getText(findElementIndex(textFieldsRoomNumber,i)).equalsIgnoreCase("DESASIGANADA");
        }
    }

    public void userVerifyUnassignedConditionAfterLogin(){
        for(int i = 0; i< getNumberOfTextFieldsRoomNumberAreDisplayed(); i++){
            getText(findElementIndex(textFieldsRoomNumber,i)).equalsIgnoreCase("DESASIGANADA");
        }
    }

    public void userModifyOneRoomNumber() {
        enterTextAndSaveInOnlyOneRoomNumber();
        setArrayTextFieldsValueAfterSaving(getValueOfAllTextFields());
    }

    public void userVerifyChangedValueInOneRoomAfterLogin(){
        setArrayTextFieldsValueAfterLogin(getValueOfAllTextFields());
        Assert.assertFalse(getArrayTextFieldsValueBeforeSaving().equals(getArrayTextFieldsValueAfterLogin()));
    }

    public void userModifyAllRoomsNumber() {
        enterTextAndSaveInAllRoomNumber();
        setArrayTextFieldsValueAfterSaving(getValueOfAllTextFields());
    }

    public void userVerifyAllRoomsNumberAfterSaving() {
        Assert.assertTrue(getArrayTextFieldsValueBeforeSaving().equals(getArrayTextFieldsValueAfterSaving()));
    }

    public void userVerifyAllRoomsNumberAfterSavingOnlyOneRoom() {
        Assert.assertFalse(getArrayTextFieldsValueBeforeSaving().equals(getArrayTextFieldsValueAfterSaving()));
    }

    public void userVerifyModifiedRoomsNumberAfterLogin() {
        setArrayTextFieldsValueAfterLogin(getValueOfAllTextFields());
        Assert.assertTrue(getArrayTextFieldsValueAfterSaving().equals(getArrayTextFieldsValueAfterLogin()));
    }

    public void userVerifyCameraStreaming() throws IOException, InterruptedException {
    accessCamera();
    }

    public void userAccessAddPatientPage() throws IOException, InterruptedException {
        accessAssignCamera();
    }
}
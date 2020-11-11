package tests.Enfermeras;

import com.automation.remarks.testng.UniversalVideoListener;
import data.Data;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.Retry;

import java.io.IOException;
import java.sql.SQLException;

import static data.Data.LoginUsers;

@Listeners(UniversalVideoListener.class)
public class RolNeonatosPadresMadres extends BaseTest {


   // @Description("Caso de prueba de verificación de validaciones pantalla log in")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_001_Validaciones_login() throws InterruptedException {
        LoginUsers data = new LoginUsers();
        loginPage.userVerifyWrongCredentials(data.ROL_1_NURSE_NEONATOS_WRONG_PASS);
        loginPage.userVerifySubmitButtonIsEnabled();
    }

    //@Description("Caso de prueba de verificación de validaciones pantalla log in y pantalla de asignación")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_002_Validaciones_asignación_cámara() throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        LoginUsers data = new LoginUsers();
        Data.AddParentsPage dataParents = new Data.AddParentsPage();

        loginPage.userLogin(data.ROL_1_NURSE_NEONATOS);
        camerasListPage.userVerifyCameraNumbers(data.ROL_1_NURSE_NEONATOS.userName);
        camerasListPage.userAccessAddPatientPage();
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.MISSING_PARENT_NAME);
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.MISSING_CHILD_NAME);
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.WRONG_EMAIL_FORMAT_MISSING_AT);
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.WRONG_EMAIL_FORMAT_MISSING_DOT_COM);
        addPatientPage.userVerifyFomularyDataValidation(dataParents.MISSING_PARENT_SURNAME);
        addPatientPage.userInformFormularyData(dataParents.RIGHT_PERSONAL_DATA);
        addPatientPage.userVerifyConfirmPageData();
        addPatientPage.userGoBackToFormularyScreen();
        addPatientPage.userVerifyFormularyFilledInInfo();
        addPatientPage.userGoBackToCamerasPage();
        camerasListPage.userVerifyCameraNumbers(data.ROL_1_NURSE_NEONATOS.userName);
    }

    //@Description("Caso de prueba de verificación de validaciones pantalla log in y pantalla de asignación")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_003_Validaciones_asignación_cámara() throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        LoginUsers data = new LoginUsers();
        Data.AddParentsPage dataParents = new Data.AddParentsPage();

        loginPage.userLogin(data.ROL_1_NURSE_NEONATOS);
        camerasListPage.userVerifyCameraNumbers(data.ROL_1_NURSE_NEONATOS.userName);
        camerasListPage.userAccessAddPatientPage();
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.MISSING_PARENT_NAME);
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.MISSING_CHILD_NAME);
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.WRONG_EMAIL_FORMAT_MISSING_AT);
        addPatientPage.userVerifyFormularyMandatoryData(dataParents.WRONG_EMAIL_FORMAT_MISSING_DOT_COM);
        addPatientPage.userVerifyFomularyDataValidation(dataParents.MISSING_PARENT_SURNAME);
        addPatientPage.userInformFormularyData(dataParents.RIGHT_PERSONAL_DATA);
        addPatientPage.userVerifyConfirmPageData();
        addPatientPage.userGoBackToFormularyScreen();
        addPatientPage.userVerifyFormularyFilledInInfo();
        addPatientPage.userGoBackToCamerasPage();
        camerasListPage.userVerifyCameraNumbers(data.ROL_1_NURSE_NEONATOS.userName);
    }
}

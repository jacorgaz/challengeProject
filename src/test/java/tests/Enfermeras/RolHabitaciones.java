package tests.Enfermeras;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.Retry;

import java.io.IOException;
import java.sql.SQLException;

import static data.Data.LoginUsers;

@Listeners(UniversalVideoListener.class)
public class RolHabitaciones extends BaseTest {

    //@Description("Caso de prueba de verificación de validaciones pantalla log in")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_001_Validaciones_login() throws InterruptedException {
        LoginUsers data = new LoginUsers();
        loginPage.userVerifyWrongCredentials(data.USER_WRONG_EMAIL);
        loginPage.userClearTextFields();
        loginPage.userVerifyWrongCredentials(data.USER_WRONG_PASS);
        loginPage.userClearTextFields();
        loginPage.userVerifySubmitButtonIsEnabled();
    }

    ////@Description("Caso de prueba de verificación de validaciones de campos como de permisos en pantalla de habitaciones")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_002_Validaciones_habitaciones() throws InterruptedException, SQLException, ClassNotFoundException {
        LoginUsers data = new LoginUsers();
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyCameraNumbers(data.ROL_4_NURSE_2.userName);
        camerasListPage.userVerifyTextFieldsFormats();
        camerasListPage.userVerifyUnassignedConditionBeforeLogout(data.ROL_4_NURSE_2);
        camerasListPage.userLogOut();
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyUnassignedConditionAfterLogin();
        camerasListPage.userModifyOneRoomNumber();
        camerasListPage.userVerifyAllRoomsNumberAfterSavingOnlyOneRoom();
        camerasListPage.userLogOut();
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyChangedValueInOneRoomAfterLogin();
        camerasListPage.userVerifyRoomNumbersInDDBBWithRoomNumberInFront(data.ROL_4_NURSE_2.userName);
    }

    //@Description("Caso de prueba de para la modificación de datos cámara/habitación")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_003_Modificar_habitaciones() throws InterruptedException, SQLException, ClassNotFoundException {
        LoginUsers data = new LoginUsers();
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyCameraNumbers(data.ROL_4_NURSE_2.userName);
        camerasListPage.userModifyAllRoomsNumber();
        camerasListPage.userVerifyAllRoomsNumberAfterSaving();
        camerasListPage.userLogOut();
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyModifiedRoomsNumberAfterLogin();
        camerasListPage.userVerifyRoomNumbersInDDBBWithRoomNumberInFront(data.ROL_4_NURSE_2.userName);
    }

    //@Description("Caso de prueba de para la modificación de datos cámara/habitación por varios usuarios")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_004_Modificar_habitaciones_por_otro_usuario() throws InterruptedException, SQLException, ClassNotFoundException {
        LoginUsers data = new LoginUsers();
        loginPage.userLogin(data.ROL_4_NURSE_4);
        camerasListPage.userModifyAllRoomsNumber();
        camerasListPage.userVerifyAllRoomsNumberAfterSaving();
        camerasListPage.userLogOut();
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userModifyAllRoomsNumber();
        camerasListPage.userVerifyAllRoomsNumberAfterSaving();
        camerasListPage.userLogOut();
        loginPage.userLogin(data.ROL_4_NURSE_4);
        camerasListPage.userVerifyModifiedRoomsNumberAfterLogin();
    }

    //@Description("Caso de prueba de para la modificación de datos cámara/habitación por varios usuarios")
    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_005_Modificar_hospital() throws InterruptedException, SQLException, ClassNotFoundException {
        LoginUsers data = new LoginUsers();
        camerasListPage.userChangeIDHospitalInBBDD(data.ROL_4_NURSE_2.userName);
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyRoomsAfterChangingHospitalInBBDD(data.ROL_4_NURSE_2.userName);
    }

    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    //@Description("Caso de prueba de para la modsificación de datos cámara/habitación por varios usuarios")
    @Video
    public void test_TC_007_Ver_camara () throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        LoginUsers data = new LoginUsers();
        camerasListPage.userChangeIDHospitalInBBDD(data.ROL_4_NURSE_2.userName);
        loginPage.userLogin(data.ROL_4_NURSE_2);
        camerasListPage.userVerifyCameraStreaming();
    }
}

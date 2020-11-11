package tests.Enfermeras;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.Retry;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import static data.Data.LoginUsers;

@Listeners(UniversalVideoListener.class)
public class DummyTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_001_Validaciones_login() throws InterruptedException {
        System.out.println("EL test es test_TC_001_Validaciones_login");
    }

    @Test(retryAnalyzer = Retry.class, groups = {"Regression"})
    public void test_TC_002_Validaciones_habitaciones() throws InterruptedException, SQLException, ClassNotFoundException {
        System.out.println("EL test es test_TC_002_Validaciones_habitaciones");
    }
}

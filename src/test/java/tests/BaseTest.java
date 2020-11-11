package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AddPatientPage;
import pages.BasePage;
import pages.CamerasListPage;
import pages.LoginPage;
import utils.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static utils.Constants.DOCKER_BROWSERS;
import static utils.DriverManager.*;
import static utils.GetScreenShot.capture;
import static utils.GlobalVariables.setFailExecutionStatus;

public class BaseTest extends BasePage {
    private static ExtentReports extentReport = new ExtentReports();
    private ExtentTest parentTest;
    private static final String browserExecution=  PropertyManager.getInstance().getBrowser();
    private static String environment = PropertyManager.getInstance().getEnvironment();
    private static String url = PropertyManager.getInstance().getUrl();
    protected static String headless = PropertyManager.getInstance().getHeadlessAutomation();
    protected static boolean automation = PropertyManager.getInstance().getAutomation();

    public LoginPage loginPage;
    public CamerasListPage camerasListPage;
    public AddPatientPage addPatientPage;

    private void configScreenShot(){
        File screenshotDirectory = new File("./screenshots");
        File testReportDirectory = new File("./TestOutput");
        File videoDirectory = new File("./video");

        if (testReportDirectory.exists()) {
            testReportDirectory.delete();
        } else {
            testReportDirectory.mkdir();
        }
        if (screenshotDirectory.exists()) {
            screenshotDirectory.delete();
        } else {
            screenshotDirectory.mkdir();
        }
        if (videoDirectory.exists()) {
            videoDirectory.delete();
        } else {
            videoDirectory.mkdir();
        }
    }

    @BeforeSuite()
    public void beforeSuite() {
        /*//configExtentReportAndKlovServer(KLOVIP,KLOVPORT,PROJECTNAME, extentReport);
        ExtentKlovReporter klov = new ExtentKlovReporter();
        klov.initMongoDbConnection(KLOVIP, KLOVPORT);
        klov.setProjectName(PROJECTNAME+"_"+environment);
        klov.initKlovServerConnection(KLOVURL);*/
        configScreenShot();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./TestOutput/SuiteTestReport.html");
        //extentReport.attachReporter(htmlReporter, klov);
        extentReport.attachReporter(htmlReporter);
        htmlReporter.loadConfig("./extent-config.xml");
        htmlReporter.config().setDocumentTitle("Selenium Extent Report");
    }

    @Parameters(value = {"browser"})
    @BeforeClass
    public void beforeClass(@Optional String browser, ITestContext context, Method method) {
        parentTest =extentReport.createTest("Execution for "+ browser +" "+context.getName());
    }

    @BeforeMethod
    @Parameters(value={"browser"})
    public void beforeMethod(Method method, @Optional String browser, ITestContext context) throws MalformedURLException {
        try{
            ExtentReportManager.setTestName(method.getName());
            ExtentReportManager.getTestName();
            ExtentTest extentTest = parentTest.createNode(ExtentReportManager.getTestName());
            ExtentReportManager.setExtentTest(extentTest);
            createNewDriverInstance(browser);
            System.out.println((char)27 + "[34m"+"•••••• [SELENIUM] ("+ getBrowserName()+Thread.currentThread().getId()+") ==> test "+ ExtentReportManager.getTestName()+context.getName()+ " has started"+ (char)27 + "[39m");
            if(DOCKER_BROWSERS.contains(getBrowserName())){
                String urlLive = "http://10.1.0.41:8080/#/sessions/"+DriverManager.getDriver().getSessionId();
                System.out.println((char)27 + "[34m"+"•••••• [SELENIUM] (Ver la sesión LIVE en  "+ urlLive);
            }
            DriverManager.getDriver().navigate().to(url);
            getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            loginPage = new LoginPage();
            camerasListPage= new CamerasListPage();
            addPatientPage= new AddPatientPage();
        }catch (Exception e){
            ExtentReportManager.getExtentTest().info("Error en beforeMethod "+ e);
            setFailExecutionStatus(true);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        ExtentTest extentTest = ExtentReportManager.getExtentTest();
        try
        {
            if (result.getStatus() == ITestResult.FAILURE|| GlobalVariables.getFailExecutionStatus()) {
                extentTest.fail(MarkupHelper.createLabel("Test failed", ExtentColor.RED));
                extentTest.fail("<code>" + result.getThrowable() + "</code>");
                extentTest.fail("", capture("_fail"));
                extentTest.info(getSelenoidVideos());
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip(MarkupHelper.createLabel("Test skipped", ExtentColor.ORANGE));
                extentTest.skip("<code>" + result.getThrowable() + "</code>");
                extentTest.skip("", capture("_skipped"));
                extentTest.info(getSelenoidVideos());

            } else {
                extentTest.pass(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
                extentTest.pass("", capture("_pass"));
                extentTest.info(getSelenoidVideos());
            }
            switch (result.getStatus()){
                case 1:
                    System.out.println((char)27 + "[32m"+"•••••• [SELENIUM] ("+ getBrowserName()+Thread.currentThread().getId()+") <== test "+ ExtentReportManager.getTestName()+" OK" + (char)27 + "[39m");
                    break;
                case 2:
                    System.out.println((char)27 + "[31m"+"•••••• [SELENIUM] ("+ getBrowserName()+Thread.currentThread().getId()+") <== test "+ ExtentReportManager.getTestName()+" KO" + (char)27 + "[39m");
                    break;
                case 3:
                    System.out.println((char)27 + "[33m"+"•••••• [SELENIUM] ("+ getBrowserName()+Thread.currentThread().getId()+") <== test "+ ExtentReportManager.getTestName()+" SKIPPED" + (char)27 + "[39m");
                    break;
            }
        }
        catch(Exception e)
        {
            extentTest.fail(MarkupHelper.createLabel("Test unknown error", ExtentColor.RED));
            extentTest.fail("<code>" + result.getThrowable() + "</code>");
        }

        try{
            if(!getDriver().toString().contains("null")){
                WebDriver driver = getDriver();
                driver.quit();
            }
        }catch (Exception e){
            ExtentReportManager.getExtentTest().info("Error en driver Quit "+ e);
        }
    }

    private void createNewDriverInstance(String browser) throws MalformedURLException {
        String mode = System.getProperty("mode");
        String browserToExecute = "";
        DesiredCapabilities  capabilities = new DesiredCapabilities();
        if(mode.equalsIgnoreCase("debug")){
            browserToExecute = browserExecution;
        }else {
            browserToExecute=browser;
        }
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserToExecute);
        setWebDriver(DriverFactory.createNewDriverInstance(browserToExecute));
        setBrowserName(browserToExecute);
    }

    @AfterSuite
    public void afterSuite() {
        extentReport.flush();
    }
}

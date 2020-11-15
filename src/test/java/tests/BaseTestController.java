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
import pages.*;
import utils.*;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static pages.BaseCommands.getSelenoidVideos;
import static utils.Constants.DOCKER_BROWSERS;
import static utils.DriverManager.*;
import static utils.GetScreenShot.capture;
import static utils.GlobalVariables.setFailExecutionStatus;

public class BaseTestController {
    private static final ExtentReports EXTENT_REPORTS = new ExtentReports();
    private ExtentTest parentTest;
    private static final String BROWSER_EXECUTION =  PropertyManager.getInstance().getBrowser();
    private static final String URL = PropertyManager.getInstance().getUrl();
    protected static String headless = PropertyManager.getInstance().getHeadlessAutomation();


    public static HomePage homePage;
    public static ProductPage productPage;
    public static CartPage cartPage;
    public static PointsProgramPage pointsProgramPage;
    public static ProductPage getHomePage() {
        return productPage;
    }


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
        configScreenShot();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./TestOutput/SuiteTestReport.html");
        EXTENT_REPORTS.attachReporter(htmlReporter);
        htmlReporter.loadConfig("./extent-config.xml");
        htmlReporter.config().setDocumentTitle("Selenium Extent Report");
    }

    @Parameters(value = {"browser"})
    @BeforeClass
    public void beforeClass(@Optional String browser, ITestContext context, Method method) {
        parentTest = EXTENT_REPORTS.createTest("Execution for "+ BROWSER_EXECUTION +" "+context.getName());
    }

    @BeforeMethod
    @Parameters(value={"browser"})
    public void beforeMethod(Method method, @Optional String browser, ITestContext context) {
        try{
            ExtentReportManager.setTestName(method.getName());
            ExtentReportManager.getTestName();
            ExtentTest extentTest = parentTest.createNode(ExtentReportManager.getTestName());
            ExtentReportManager.setExtentTest(extentTest);
            createNewDriverInstance();
            System.out.println((char)27 + "[34m"+"•••••• [SELENIUM] ("+ getBrowserName()+Thread.currentThread().getId()+") ==> test "+ ExtentReportManager.getTestName()+context.getName()+ " has started"+ (char)27 + "[39m");
            if(DOCKER_BROWSERS.contains(getBrowserName())){
                String urlLive = Constants.SELENOID_DOCKER_LIVE_STREAMING+DriverManager.getDriver().getSessionId();
                System.out.println((char)27 + "[34m"+"•••••• [SELENIUM] (Live Streaming in "+ urlLive);
            }
            DriverManager.getDriver().navigate().to(URL);
            getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            homePage = new HomePage();
            productPage = new ProductPage();
            cartPage = new CartPage();
            pointsProgramPage = new PointsProgramPage();

        }catch (Exception e){
            ExtentReportManager.getExtentTest().info("Error en beforeMethod "+ e);
            setFailExecutionStatus(true);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) {
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

    private void createNewDriverInstance() throws MalformedURLException {
        String browserToExecute = "";
        DesiredCapabilities  capabilities = new DesiredCapabilities();
        browserToExecute = BROWSER_EXECUTION;
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserToExecute);
        setWebDriver(DriverFactory.createNewDriverInstance(browserToExecute));
        setBrowserName(browserToExecute);
    }

    @AfterSuite
    public void afterSuite() {
        EXTENT_REPORTS.flush();
    }
}

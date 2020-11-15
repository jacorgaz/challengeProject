package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static utils.GlobalVariables.setFailExecutionStatus;


public class DriverFactory {
    public synchronized static WebDriver createNewDriverInstance(String browser) throws MalformedURLException {
        String  hubURL;
        WebDriver driver  = null;
        try {
            switch (browser) {
                case "chromeMac":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(OptionsManager.getChromeOptions());
                    driver.manage().window().maximize();
                    break;
                case "firefoxMac":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
                    driver.manage().window().maximize();
                    break;
                case "chromeDocker":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeLinuxOptions = new ChromeOptions();
                    chromeLinuxOptions.setCapability("browserName", "chrome");
                    chromeLinuxOptions.setCapability("version", "80.0");
                    chromeLinuxOptions.setCapability("enableVNC", true);
                    chromeLinuxOptions.setCapability("enableVideo", true);
                    driver = new RemoteWebDriver(URI.create("http://192.168.1.110:4444/wd/hub").toURL(),
                            chromeLinuxOptions);
                    OptionsManager.getChromeOptions();
                    driver.manage().window().maximize();
                    break;
                case "firefoxWin":
                    FirefoxOptions fireFoxCap = new FirefoxOptions();
                    //driver = new RemoteWebDriver(new URL(Constants.HUBURL), fireFoxCap);
                    driver = new RemoteWebDriver(OptionsManager.getFirefoxOptions());
                    driver.manage().window().maximize();
                    break;
                case "chromeWin":
                    ChromeOptions chromeCap = new ChromeOptions();
                    //driver = new RemoteWebDriver(new URL(Constants.HUBURL), chromeCap);
                    driver = new ChromeDriver(OptionsManager.getChromeOptions());
                    driver.manage().window().maximize();
                    break;
                case "chromeMobile":
                    ChromeOptions mobileChromeLinuxOptions = new ChromeOptions();
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "Pixel 2");
                    mobileChromeLinuxOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                    mobileChromeLinuxOptions.setCapability("browserName", "chrome");
                    mobileChromeLinuxOptions.setCapability("version", "latest");
                    mobileChromeLinuxOptions.setCapability("enableVNC", true);
                    mobileChromeLinuxOptions.setCapability("enableVideo", true);
                    driver = new RemoteWebDriver(URI.create("http://10.1.0.41:4444/wd/hub").toURL(),
                            mobileChromeLinuxOptions);
                    OptionsManager.getChromeOptions();
                    break;
                case "firefoxDocker":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxLinux = new FirefoxOptions();
                    firefoxLinux.setCapability("browserName", "firefox");
                    firefoxLinux.setCapability("version", "latest");
                    firefoxLinux.setCapability("enableVNC", true);
                    firefoxLinux.setCapability("enableVideo", false);
                    driver = new RemoteWebDriver(URI.create("http://10.1.0.41:4444/wd/hub").toURL(),
                            firefoxLinux);
                    driver.manage().window().maximize();
                    break;
                case "safari":
                    try {
                        SafariOptions safariOptions = new SafariOptions();
                        safariOptions.setCapability("browserName", "safari");
                        safariOptions.setCapability("version", "latest");
                        safariOptions.setCapability("enableVNC", true);
                        driver = new RemoteWebDriver(URI.create("http://10.3.5.202:4444/wd/hub").toURL(),
                                safariOptions);
                        driver.manage().window().maximize();
                    }catch (Exception e){
                        System.out.println("El error es "+e);
                    }
                    break;
                case "edge":
                        EdgeOptions caps = new EdgeOptions();
                        driver = new RemoteWebDriver(new URL("http://192.168.1.110:4444/"), caps);
                    break;
                case "iPhone":
                    driver = new SafariDriver();
                    driver.manage().window().setSize(new Dimension(375, 667));
                    break;
                case "ie":
                    hubURL = "http://10.3.5.202:4444/wd/hub";
                    ChromeOptions ieCap = new ChromeOptions();
                    driver = new RemoteWebDriver(new URL(hubURL), ieCap);
                    driver.manage().window().maximize();
                    break;
                default:
                    setFailExecutionStatus(true);
                    ExtentReportManager.getExtentTest().info("El browser no ha sido encontrado " + browser);
                    break;
            }
            setFailExecutionStatus(false);
            return driver;

        } catch (WebDriverException e) {
            setFailExecutionStatus(true);
            ExtentReportManager.getExtentTest().info("Error en DriverFactory para el browser " + browser+" "+ e);
        }
        return driver;
    }
}
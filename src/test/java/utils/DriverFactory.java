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

import static utils.Constants.SELENOID_DOCKER_HUB;
import static utils.GlobalVariables.setFailExecutionStatus;


public class DriverFactory {
    public synchronized static WebDriver createNewDriverInstance(String browser) throws MalformedURLException {
        WebDriver driver  = null;
        try {
            switch (browser) {
                case "chromeDocker":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeLinuxOptions = new ChromeOptions();
                    chromeLinuxOptions.setCapability("browserName", "chrome");
                    chromeLinuxOptions.setCapability("version", "80.0");
                    chromeLinuxOptions.setCapability("enableVNC", true);
                    chromeLinuxOptions.setCapability("enableVideo", true);
                    driver = new RemoteWebDriver(URI.create(SELENOID_DOCKER_HUB).toURL(),
                            chromeLinuxOptions);
                    OptionsManager.getChromeOptions();
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
                    driver = new RemoteWebDriver(URI.create(SELENOID_DOCKER_HUB).toURL(),
                            mobileChromeLinuxOptions);
                    OptionsManager.getChromeOptions();
                    break;
                case "firefoxDocker":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxLinux = new FirefoxOptions();
                    firefoxLinux.setCapability("browserName", "firefox");
                    firefoxLinux.setCapability("version", "80.0");
                    firefoxLinux.setCapability("enableVNC", true);
                    firefoxLinux.setCapability("enableVideo", true);
                    driver = new RemoteWebDriver(URI.create(SELENOID_DOCKER_HUB).toURL(),
                            firefoxLinux);
                    driver.manage().window().maximize();
                    break;
                case "firefoxMac":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
                    driver.manage().window().maximize();
                    break;
                default:
                    setFailExecutionStatus(true);
                    ExtentReportManager.getExtentTest().info("Browser has not been found  " + browser);
                    break;
            }
            setFailExecutionStatus(false);
            return driver;

        } catch (WebDriverException e) {
            setFailExecutionStatus(true);
            ExtentReportManager.getExtentTest().info("Error in DriverFactory for browser " + browser+" "+ e);
        }
        return driver;
    }
}
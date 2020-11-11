package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    static InheritableThreadLocal<RemoteWebDriver> webDriver = new InheritableThreadLocal<>();
    static InheritableThreadLocal<String> baseUrl =new InheritableThreadLocal<>();
    static InheritableThreadLocal<String> browserName = new InheritableThreadLocal<>();

    public static RemoteWebDriver getDriver() {
        return webDriver.get();
    }

    public static String getBaseUrl() {
        return baseUrl.get();
    }

    public static String getBrowserName() {
        return browserName.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set((RemoteWebDriver) driver);
    }

    public static void setWebDriver(WebDriver driver, String url) {
        webDriver.set((RemoteWebDriver) driver);
        baseUrl.set(url);
    }

    public static void setBrowserName(String name) {
        browserName.set(name);
    }

}

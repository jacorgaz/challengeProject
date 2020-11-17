package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
    static InheritableThreadLocal<RemoteWebDriver> webDriver = new InheritableThreadLocal<>();
    static InheritableThreadLocal<String> browserName = new InheritableThreadLocal<>();

    public static RemoteWebDriver getDriver() {
        return webDriver.get();
    }
    public static String getBrowserName() {
        return browserName.get();
    }
    public static void setWebDriver(WebDriver driver) {
        webDriver.set((RemoteWebDriver) driver);
    }
    public static void setBrowserName(String name) {
        browserName.set(name);
    }

}

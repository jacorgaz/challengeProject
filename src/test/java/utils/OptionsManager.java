package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import tests.BaseTest;

public class OptionsManager extends BaseTest {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("applicationCacheEnabled", false);
        //Debug execution
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("chromedriver --whitelisted-ips=");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("window-size=1920,1080");
            return options;
        }
        //Debug execution
        if (headless.equalsIgnoreCase("false")) {
            options.addArguments("chromedriver --whitelisted-ips=");
            options.setHeadless(false);
            return options;
        }

        if (automation) {
            //options.setHeadless(true);
            options.addArguments("chromedriver --whitelisted-ips=");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920x1080");
            options.addArguments("--remote-debugging-port=4444");
            return options;
        }
        return options;
    }

    static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        //Debug execution
        if (headless.equalsIgnoreCase("true")) {
            options.setHeadless(false);
            options.addArguments("window-size=1920,1080");
            return options;
        }
        //Debug execution
        if (headless.equalsIgnoreCase("false")) {
            options.setHeadless(false);
            return options;
        }

        if (automation) {
            options.setHeadless(true);
            return options;
        }
        return options;
    }
}


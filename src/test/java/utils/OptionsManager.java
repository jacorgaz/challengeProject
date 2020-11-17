package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import tests.BaseTestController;

public class OptionsManager extends BaseTestController {

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

        return options;
    }
}


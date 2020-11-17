package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class PropertyManager {

    //**********************************************************************************************************
    //Description: PropertyManager class reads global configurations and use them during test execution.
    //**********************************************************************************************************
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir") + "/src/test/java/resources/configuration.properties";
    private static String videoPath = System.getProperty("user.dir") + "/src/test/java/resources/video.properties";
    private static String environment;
    private static String numberOfItemsToPurchase;
    private static String browser;
    private static String headless = System.getProperty("headless");
    private static String threads;
    private static String url;
    private String suite;
    private String label;
    private static boolean automation;

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }

    //Get all configuration data and assign to related fields.
    private void loadData() {
        //Declare a properties object
        Properties prop = new Properties();

        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        //Get profile from pom
        String mode = System.getProperty("mode");

        if(mode==null){
            browser =  System.getProperty("driver.class");
            if(headless==null){
                headless= "false";
            }
            numberOfItemsToPurchase = prop.getProperty("numberOfItemsToPurchase");
            environment = System.getProperty("dev.env");
            url = System.getProperty("base.url");
        }else {
            numberOfItemsToPurchase = prop.getProperty("numberOfItemsToPurchase");
            automation = true;
            browser =  System.getProperty("driver.class");
            url = System.getProperty("base.url");
            environment = System.getProperty("dev.env");
            suite = System.getProperty("suiteToTest");
            label = System.getProperty("label");
            threads = prop.getProperty("threads");
        }
    }

    public String getEnvironment() {
        return environment;
    }

    public String getHeadlessAutomation() {
        return headless;
    }

    public boolean getAutomation() {
        return automation;
    }

    public String getUrl() {
        return url;
    }

    public String getBrowser() {
       return browser;
    }

    public String getThreads() { return  threads; }

    public String getLabel() {
        return label;
    }

    public String getSuite() {
        return suite;
    }

    /**
     * @param browser is retrieved from testng xml while parallel execution
     * For debug browser is retrieved from om xml
     */
    public String getBrowseName(String browser){
        if (automation){
            return browser;
        }else {
            return PropertyManager.getInstance().getBrowser();
        }
    }

    public  String getNumberOfItemsToPurchase() {
        return numberOfItemsToPurchase;
    }

}
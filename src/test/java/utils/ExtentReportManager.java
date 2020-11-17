package utils;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager {
    private  static ThreadLocal <ExtentTest> extentTest = new ThreadLocal<>();
    private  static ThreadLocal <String> testName = new ThreadLocal<>();
    public static void setExtentTest (ExtentTest test) {
        extentTest.set(test);
    }
    public static  void setTestName(String name) {
        testName.set(name);
    }
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
    public static String getTestName() {
        return testName.get();
    }
}

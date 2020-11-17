package tests;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utils.PropertyManager;

import java.lang.reflect.Method;
import java.util.*;

import static utils.Constants.TESTCLASSES;

public class LaunchTests {

    static HashMap<String, ArrayList<String>> classAndTestsIncluded = new HashMap<>();
    static ArrayList<String> browsers = new ArrayList<>();
    static String suite = PropertyManager.getInstance().getSuite();
    static String selectedBrowsers = PropertyManager.getInstance().getBrowser();
    static String label = PropertyManager.getInstance().getLabel();
    static String threads = PropertyManager.getInstance().getThreads();
    static String[] allSuites = TESTCLASSES;
    static String[] testSuites;

    private static Class getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used return the all the test methods in the class.
     * If System property (Still to define name) is defined with regression or sanity it´ll iterate over all the classes
     in classesWithTestsForRegressionOrSanityTests to find tests with regression or sanity groups defined. If not, it will select the class pass through POM "suiteToTest"
     * @param classToExtractTestMethodsFrom This is the class string selected to convert to class object and obtain the tests methods from.
     * @return Class array with all the test methods to execute in class
     */
    private static ArrayList<String> getMethodsInClass(String classToExtractTestMethodsFrom) throws ClassNotFoundException {
        Test testClass;
        String groupName = "";
        String methodName;
        Method[] methods;
        ArrayList<String> methodsToAddToClasses = new ArrayList<>();
        methods = getClass(classToExtractTestMethodsFrom).getMethods();
        // Get the name of every method present in the list
        for (Method method : methods) {
            methodName = method.getName();
            if (methodName.toLowerCase().contains("test") && !methodName.toLowerCase().contains("before")) {
                if (label.toLowerCase().equals("none")) {
                    methodsToAddToClasses.add(methodName);
                } else {
                    testClass = method.getAnnotation(Test.class);
                    for (int i = 0; i < testClass.groups().length; i++) {
                        groupName = testClass.groups()[i].toLowerCase();
                        if (groupName.contains(label.toLowerCase())) {
                            methodsToAddToClasses.add(methodName);
                        }
                    }
                }
            }
        }
        if (methodsToAddToClasses.size() == 0) {
            methodsToAddToClasses.add("No tiene tests");
        }
        orderTestsInClass(methodsToAddToClasses);
        return methodsToAddToClasses;
    }

    /**
     * This method is to order the tests due to not always the same order is returned.
     * @param theList array is the array of tests to order
     * @return the list ordered
     */
    private static void orderTestsInClass(List theList) {
        Collections.sort(theList);
    }

    /**
     * This method is used to set the tests to be executed in each test class.
     */

    public static void setTestsToExecuteInEachTestClass() throws ClassNotFoundException {
        int i;
        for (i = 0; i < testSuites.length; i++) {
            String className = testSuites[i];
            classAndTestsIncluded.put(className, getMethodsInClass(className));
        }
    }

    public static ArrayList browserList() {
        String countNumberOfBrowsers = selectedBrowsers;
        long count = countNumberOfBrowsers.chars().filter(ch -> ch == '_').count();
        String[] browserSplit = countNumberOfBrowsers.split("_");
        browsers.addAll(Arrays.asList(browserSplit).subList(0, (int) (count + 1)));
        return browsers;
    }

    public static void run(String browsers) throws ClassNotFoundException {
        TestNG testng = new TestNG();
        ArrayList<XmlTest> tests = new ArrayList<XmlTest>();
        List<XmlSuite> suites = new ArrayList<>();
        XmlSuite suite = new XmlSuite();
        suite.setName("Test-class "+browsers);
        suite.setParallel(XmlSuite.ParallelMode.CLASSES);
        setTestsToExecuteInEachTestClass();
        XmlTest test = new XmlTest();
        Map<String,String> testngParams = new HashMap<String,String>();
        //Create every single test and all its classes xml to be added to the suite and added the test to execute
            test.setName(browsers);
            testngParams.put("browser", browsers);
            test.setParameters(testngParams);
        for (String testSuite : testSuites) {
            List<XmlClass> classes = new ArrayList<>();
            List<XmlInclude> includeMethods = new ArrayList<>();
            XmlClass testClass = new XmlClass(getClass(testSuite).getName());
            for (String methods : getMethodsInClass(testSuite)) {
                includeMethods.add(new XmlInclude(methods));
                testClass.setIncludedMethods(includeMethods);
            }
            classes.add(testClass);
            test.setXmlClasses(classes);
            tests.add(test);
            suite.setTests(tests);
            test.setSuite(suite);
        }

        suites.add(suite);
        testng.setXmlSuites(suites);
        testng.setOutputDirectory("test-output");
       if(browsers.equalsIgnoreCase("safari")){
            suite.setThreadCount(1);
        }else {
            suite.setThreadCount(Integer.parseInt(threads));
        }
        testng.run();
    }

    @Test()
    public void tests() throws ClassNotFoundException {
        ArrayList browserList = browserList();
        for (Object browser : browserList) {
            if (suite.equals("ALL")) {
                testSuites = allSuites;
                run(browser.toString());
            } else {
                testSuites = new String[]{suite};
                run(browser.toString());
            }
        }
    }
}

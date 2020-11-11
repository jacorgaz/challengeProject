package utils;

import com.google.common.collect.ImmutableList;

public class Constants {

    public static final String HUBURL = "http://localhost:4444/wd/hub";
    public static final String SCREENSHOTSPATH = "./TestOutput/screenshots/";
    public static final String GRIDNODE = "http:/10.0.2.15:4444/wd/hub";
    public static final String SELENOIDDOCKERVIDEO = "http://10.1.0.41:4444";
    public static final String SELENOIDSAFARI = "http://10.3.5.202:4444/wd/hub";
    public static final String KLOVIP = "10.1.100.17";
    public static final int KLOVPORT = 27017;
    public static final String PROJECTNAME = "Temaplate";
    public static final String EXCELSHEET = "TestData";
    public static final String KLOVURL = "http://extendereports.mo2o.com";
    public static String[] TESTCLASSES = {"tests.Enfermeras.RolHabitaciones"};
    public static final ImmutableList<String> DOCKER_BROWSERS = ImmutableList.of(
            "chromeDocker",
            "chromeMobile",
            "firefoxDocker");
    public static final ImmutableList<String> DOCKER_BROWSERS_ONE_THREAD = ImmutableList.of(
            "safari",
            "chromeMobile",
            "firefoxDocker");
}

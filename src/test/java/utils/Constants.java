package utils;

import com.google.common.collect.ImmutableList;

public class Constants {
    public static final String SELENOID_DOCKER_VIDEO = "http://192.168.1.110:4444";
    public static final String SELENOID_DOCKER_LIVE_STREAMING = " http://192.168.1.110:8080/#/sessions/";
    public static final String SELENOID_DOCKER_HUB = "http://192.168.1.110:4444/wd/hub";


    public static String[] TESTCLASSES = {"tests.Enfermeras.RolHabitaciones"};
    public static final ImmutableList<String> DOCKER_BROWSERS = ImmutableList.of(
            "chromeDocker",
            "chromeMobile",
            "firefoxDocker");
}

package utils;

import com.google.common.collect.ImmutableList;

public class Constants {
    public static final String SELENOID_DOCKER_VIDEO = "http://192.168.1.110:8080/video/";
    public static final String SELENOID_DOCKER_LIVE_STREAMING = " http://192.168.1.110:8080/#/sessions/";
    public static final String SELENOID_DOCKER_HUB = "http://192.168.1.110:4444/wd/hub";


    public static String[] TESTCLASSES = {"tests.RegressionTests"};
    public static final ImmutableList<String> DOCKER_BROWSERS = ImmutableList.of(
            "chromeDocker",
            "chromeMobile",
            "firefoxDocker");

    public static final ImmutableList<String> PROGRAM_POINTS_IMAGE_TEXT = ImmutableList.of(
            "Collect with each purchase",
            "They soon add up",
            "Redeem your zooPoints");

    public static final ImmutableList<String> PROGRAM_POINTS_STEP_DESCRIPTION = ImmutableList.of(
            "It's FREE! Registered customers automatically earn zooPoints on every purchase.",
            "Easy calculation €1.00 = 1 zooPoint. zooPoints are credited after order has been paid.",
            "Choose your free zooPoints Reward in our Rewards Shop and add it to your order.");
}

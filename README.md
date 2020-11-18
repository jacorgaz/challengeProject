# Running tests

## Run/debug tests from intellij runner:

Available browsers  |
------------- |
firefox  |
chrome   |

1. In **Maven profile** select a browser (Only one can be run)
2. In **Maven profile** select whether running tests in headless mode
3. Go to src/test/java/tests/PointsProgramTest.java to run zooPoints programm tests
4. Go to src/test/java/tests/AddCartTests.java to run articles to the cart tests

**We can watch the running tests live streaming from *Run* or *Debug* console by clicking on the URL generated.**


## Run tests from Maven:

Available browsers  |
------------- |
firefox  |
chrome   |

**Headless on and one browser-**: ```mvn clean test -q  -Dsurefire.suiteXmlFiles=testSuites/launchTests.xml  -Dheadless=true -Dlabel=none -Ddriver.class=chromeDocker -DsuiteToTest=Regression```

**Headless off and multiple browsers-**: ```mvn clean test -q  -Dsurefire.suiteXmlFiles=testSuites/launchTests.xml  -Dheadless=false -Dlabel=none -Ddriver.class=chromeDocker_firefoxDocker -DsuiteToTest=Regression```

**We can watch the running tests live streaming from the terminal console by clicking on the URL generated.**

## Check test results:

Report is generated under ./extentReportTestOuput/SuiteTestReport.html*.

Open SuiteTestReport.html preferably with Chrome



#  Lift scenarios

Test plan file is located *src/test/java/tests/LiftTestPlan*

#  login feature scenarios

Test plan file is located *src/test/java/tests/LoginTestPlan*

#  newsletter feature  feature scenarios

Test plan file is located *src/test/java/tests/NewsletterTestPlan*

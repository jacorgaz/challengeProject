# Running tests

## Run/debug tests from intellij runner:

Available browsers  |
------------- |
firefoxDocker  |
chromeDocker  |
firefoxMac  |

1. In **Maven profile** select a browser (Only one can be run)
2. In **Maven profile** select whether running tests in headless mode
3. Go to src/test/java/tests/RegressionTests.java and select the tests we want to run

We can watch the running tests live streaming from *Run* or *Debug* console by clicking on the URL generated.


## Run tests from Maven:

Available browsers |
------------- |
firefoxDocker  |
chromeDocker  |
firefoxMac  |

**Headless on and one browser-**: ```mvn clean test -q  -Dsurefire.suiteXmlFiles=testSuites/launchTests.xml  -Dheadless=true -Dlabel=none -Ddriver.class=chromeDocker -DsuiteToTest=Regression```

**Headless off and multiple browsers-**: ```mvn clean test -q  -Dsurefire.suiteXmlFiles=testSuites/launchTests.xml  -Dheadless=false -Dlabel=none -Ddriver.class=chromeDocker_firefoxDocker -DsuiteToTest=Regression```

## Check test results:

Report is generated under *TestOutput/SuiteTestReport.html*.

To see the screenshots taken during the execution move folder **projectPath/screenshots** into **projectPath/TestOutput** folder.

Open SuiteTestReport.html preferably with Chrome

The results of the tests and video of the execution along with screen captures and info is displayed
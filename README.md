

![](https://www.mo2o.com/wp-content/uploads/2019/02/logo_o2o.png
)
# Features

- **Selenium** para tests en **paralelo**
- **Selenoid** para lanzar pruebas con ***Docker***
- **Selenium grid** para la ejecución de pruebas en máquinas remotas
- Features mas comunes de **TestNG**   
    - Agrupar tests
    - Casos de pruebas dependientes en TestNG  
    - Priorización de tests
    - Omitir pruebas
    - DataProvider
 - Ejemplos de tests :
    
    -   Selección de dias en un calendario
    -   Seleccionar checkbox
    -   Descargar archivos
    -   Subir archivos
    -   Contenido dinámico en página
    -   Drag&Drop
    -   Formularios
    -   Navegar entre frames


## Configurar POM XML

1. Configurar XML donde **base.url** será la web entornos donde lanzar las pruebas y **driver.class** los navegadores a usar

## Lanzar tests desde línea de comandos 

1. Para Web mvn clean test -P {dev/pro} -Dsurefire.suiteXmlFiles={SUITE.XML}
	
	     mvn clean test -P dev -Dsurefire.suiteXmlFiles=RegressionWeb.xml
	   
	
## Lanzar tests para debug
1. Para debug **web** en Maven Profiles se debe seleccionar:

 En **Maven Profiles** se debe seleccionar
                    
Maven profile | Acción/checks a seleccionar
------------- | -------------
web  | Seleccionar para lanzar prueba web
navegador  | Navegador donde se lanzarán las pruebasc chromeMac, firefoxMac...
debug  | Mandatorio seleccionar para poder lanzar pruebas en debug
headless  | Si se desea que no se lancen los navegadores se debe seleccionar
klov  | Si se desea que los reportes se guarden en el servidor de reportes Klov

## Selenoid

PENDIENTE   
       
## Selenium-Grid

Selenium Grid es una parte de Selenium Suite que se especializa en ejecutar múltiples pruebas en diferentes navegadores, sistemas operativos y máquinas en paralelo.

Selenium Grid utiliza **hub-node** donde solo ejecuta la prueba en una sola máquina llamada **hub**,  la ejecución se realiza en diferentes **nodos**

[Ver funcionamiento ](http://20tvni1sjxyh352kld2lslvc.wpengine.netdna-cdn.com/wp-content/gallery/selenium-basics/Selenium-Grid-Architecture.png)


## Configurar Hub

 1. Descargar Selenium Server [aquí](http://docs.seleniumhq.org/download/)
 2. Configurar JSON para configurar selenium grid en máquina **Hub**

```java
{
"port": 4444,

"newSessionWaitTimeout": -1,

"servlets" : [],

"withoutServlets": [],

"custom": {},

"capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",

"registry": "org.openqa.grid.internal.DefaultGridRegistry",

"throwOnCapabilityNotPresent": true,

"cleanUpCycle": 5000,

"role": "hub",

"debug": false,

"browserTimeout": 0,

"timeout": 1800
}
```

 3. Acceder a carpeta donde alojamos **Selenium Server** y el **JSON config**  y ejecutar comando **java -jar selenium-server-standalone-<versión>.jar -role hub -hubConfig hubconfig.json** en la máquina **Hub**
 4. Selenium Grid, por defecto, usa el puerto 4444 de la Máquina Hub para su interfaz  [http://localhost:4444/grid/console](http://localhost:4444/grid/console)


## Configurar Node

1. Descargar Selenium Server  [aquí](http://docs.seleniumhq.org/download/)
2. Configurar JSON para configurar selenium grid en máquina **Node**
3. Asegurarse de que los driver para cada uno de los navegadores se hayan descargado en el directorio de la máquina **Node**:
[ChromeDriver ](http://chromedriver.chromium.org/)
[FirefoxDriver](https://github.com/mozilla/geckodriver/releases)
[MicrosoftDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/driver/)

		**JSON NODE**
```java
{
  "capabilities":
  [
    {
      "browserName": "firefox",
      "marionette": true,
      "maxInstances": 5,
      "seleniumProtocol": "Driver"
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "seleniumProtocol": "Driver"
    },
    {
      "browserName": "internet explorer",
      "platform": "WINDOWS",
      "maxInstances": 1,
      "seleniumProtocol": "Driver"
    },
    {
      "browserName": "safari",
      "technologyPreview": false,
      "platform": "MAC",
      "maxInstances": 1,
      "seleniumProtocol": "Driver"
    }
  ],
  "proxy": "org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
  "maxSession": 5,
  "port": 5555,
  "register": true,
  "registerCycle": 5000,
  "hub": "http://localhost:4444",
  "nodeStatusCheckTimeout": 5000,
  "nodePolling": 5000,
  "role": "node",
  "unregisterIfStillDownAfter": 60000,
  "downPollingLimit": 2,
  "debug": false,
  "servlets" : [],
  "withoutServlets": [],
  "custom": {}
}
```

3. Lanzar el siguiente comando:
```
java-Ddriver.chrome.driver="chromedriver.exe" -Ddriver.ie.driver="IEDriverServer.exe" -Ddriver.gecko.driver="geckodriver.exe" -jar selenium-server-standalone-<version>.jar -role node -nodeConfig node.json
```
4.  Ir a [Grid](http://localhost:4444/grid/console) y comprobar los nodos están configurados

![enter image description here](https://lh3.googleusercontent.com/8t6rb-tbJP8JDPo5clwLB9QRA9ZsbEbCHwQ3cIt-p8RPTKNVQfMf0fFDn1ozmqluipnrIe-jNquQ)

## Lanzar tests en Grids

Ejemplo de una prueba con TestNG con Máquina Mac como **Hub** y máquina Windows como **Node**

La URL en la variable 'hubURL' se puede modificar para reflejar la dirección IP de la máquina remota.

```java
package tests;

import io.github.bonigarcia.wdm.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class tests {

public static Driver driver;
  String baseURL, hubURL;
  //Setup Driver
  @BeforeTest
	public void setupTest() throws MalformedURLException {
	  hubURL = "http://10.3.2.108:4444/wd/hub";
	  DesiredCapabilities dec = DesiredCapabilities.chrome();
	  ChromeOptions chromeCap = new ChromeOptions();
	  driver = new RemoteDriver(new URL(hubURL), chromeCap);
	  driver.manage().window().maximize();
	}

    @Test
	public void datePickerTest() {
        driver.get("https://google.es");
	}
        //Close Driver
    @AfterClass
  public static void quitDriver () {
            driver.quit();
	}
 }
```
# challengeProyect

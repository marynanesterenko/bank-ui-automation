package utilities;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.logging.LogManager;

public class DriverUtilities {

    static WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogManager(DriverUtilities.class);
    public static String link = "http://18.118.14.155:8080/bank/login";

    // variable "scenario" is a parameter, which will hold the data for the scenario, which is currently running
    public static void createDriver(Scenario scenario) {

        LOGGER.info("Driver setup started");

        // this "if else if" statement will help our program to determine, where to run the tests: on local computer or remote Virtual Machine provided to us by the Sauce Labs
        // so if in our config.properties file the value of the "dbank.host" key is "localhost", then tests will run on local machine:
        if (ConfigReader.getConfigProperty("dbank.host").equalsIgnoreCase("localhost")) {
            switch (ConfigReader.getConfigProperty("dbank.browser")) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    break;
                // chrome is the most stable browser, that is why it is our default browser (or "preferred")
                default:
                    // abstract Class WebDriverManager was created by Boni Garcia, so that we don't have to manage any drivers manually
                    // instead of downloading the drivers (.exe) into the "drivers" folder in our Project Structure, we can just add a dependency to our pom.xml file
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
        // and if in our config.properties file the value of the "dbank.host" key is "saucelabs", then tests will run on Sauce Lab's VM, for which we also need to set up RemoteWebDriver
        } else if (ConfigReader.getConfigProperty("dbank.host").equalsIgnoreCase("saucelabs")) {

            // the "sauceOptions" reference variable (which is also an Object of the MutableCapabilities Class) is storing the userName and accessKey for our Sauce Labs Account:
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("userName", ConfigReader.getConfigProperty("dbank.saucelabs.userName"));
            sauceOptions.setCapability("accessKey", ConfigReader.getConfigProperty("dbank.saucelabs.accessKey"));

            // the "capabilities" reference variable here (which is also an Object of the MutableCapabilities Class) is used to store some general information (browser, version, platform):
            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", ConfigReader.getConfigProperty("dbank.saucelabs.browserName"));
            capabilities.setCapability("browserVersion", ConfigReader.getConfigProperty("dbank.saucelabs.browserVersion"));
            capabilities.setCapability("platformName", ConfigReader.getConfigProperty("dbank.saucelabs.platformName"));
            // and it also is used to pass the data held inside the "sauceOptions" variable to the hub:
            capabilities.setCapability("sauce:options", sauceOptions);

            try {
                // 1) before we create an Object of the RemoteWebDriver, we have to first provide the values, that the driver will require, and that is why this line is below;
                // 2) below we are initializing the "driver" reference variable (Object of the WebDriver Class) to a RemoteWebDriver, using the "RemoteWebDriver" Constructor
                // 3) being that Constructor is a special method, that can be parameterized - here we are passing the Selenium Grid Hub's URL and all the necessary capabilities
                driver = new RemoteWebDriver(new URL(ConfigReader.getConfigProperty("dbank.saucelabs.host.url")), capabilities);
                ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + scenario.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.getConfigProperty("dbank.baseurl"));
        LOGGER.info("Driver connecting to: " + link);
        driver.get(link);

        // here we are checking whether the driver instance is getting created (aka Browser opens)
        if (driver != null){
            LOGGER.warn("Driver instance was not created");
        }
        LOGGER.info("Driver setup and start is successful!");
    }

    public static void quitDriver(Scenario scenario){
        // below is the outer "if" statement, which is needed to check whether the run is even happening on the Sauce Labs (basically checking: is scenario running or not?)
        if (ConfigReader.getConfigProperty("dbank.host").equalsIgnoreCase("saucelabs")){

            // and this inner "if" statement is needed to determine whether the scenario being run is failed, or is passed and providing the scenario's data to Sauce Labs
            if(scenario.isFailed()){
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
            } else {
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
            }
        }
        driver.quit();
    }

    // public access modifier - accessible across the Project
    // static keyword - this method can be called by simple using it's Class name - DriverUtilities.getDriver();
    // return type - WebDriver, this method will return an instance of the WebDriver Interface
    public static WebDriver getDriver(){
        return driver;
    }
}

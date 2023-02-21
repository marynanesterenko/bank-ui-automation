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

public class DriverUtilities {

    static WebDriver driver;

    public static void createDriver(Scenario scenario) {
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
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
        } else if (ConfigReader.getConfigProperty("dbank.host").equalsIgnoreCase("saucelabs")) {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("userName", ConfigReader.getConfigProperty("dbank.saucelabs.userName"));
            sauceOptions.setCapability("accessKey", ConfigReader.getConfigProperty("dbank.saucelabs.accessKey"));

            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", ConfigReader.getConfigProperty("dbank.saucelabs.browserName"));
            capabilities.setCapability("browserVersion", ConfigReader.getConfigProperty("dbank.saucelabs.browserVersion"));
            capabilities.setCapability("platformName", ConfigReader.getConfigProperty("dbank.saucelabs.platformName"));
            capabilities.setCapability("sauce:options", sauceOptions);

            try {
                driver = new RemoteWebDriver(new URL(ConfigReader.getConfigProperty("dbank.saucelabs.host.url")), capabilities);
                ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + scenario.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.getConfigProperty("dbank.baseurl"));
    }

    public static void quitDriver(Scenario scenario){
        if (ConfigReader.getConfigProperty("dbank.host").equalsIgnoreCase("saucelabs")){
            if(scenario.isFailed()){
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
            } else {
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
            }
        }
        driver.quit();
    }
    public static WebDriver getDriver(){
        return driver;
    }
}

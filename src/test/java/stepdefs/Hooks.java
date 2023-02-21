package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.CommonMethods;
import utilities.ConfigReader;
import utilities.DriverUtilities;

public class Hooks {
    @Before
    // adding the cucumber Scenario parameter, which will hold the data for the scenario, which is currently running
    public void setUp (Scenario scenario) {
        // initializing "properties" Object, which is also inside the ConfigReader Class
        ConfigReader.initializeProperties();

        // calling the .createDriver() method from the DriverUtilities Class
        DriverUtilities.createDriver(scenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        // using the .attach() method in order to attach our screenshot to the scenario
        // the parameters, we are passing are: the byte[] array "screenshot", which is returned by the .takeScreenshot() method, inside the CommonMethods Class
        // the media type is: image/png, because this is an image of png type
        // the name of the scenario, as the name of the image
        if (scenario.isFailed()){
            scenario.attach(CommonMethods.takeScreenshot(), "image/png", scenario.getName());
        }
        CommonMethods.takeScreenshot(scenario);
        DriverUtilities.quitDriver(scenario);
    }
}

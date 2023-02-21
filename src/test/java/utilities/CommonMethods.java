package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class CommonMethods {
    public static byte[] takeScreenshot(){
        // "takeScreenshot" is an instance of the TakeScreenshot Interface
        // TakeScreenshot Interface is similar to the way JSExecutor is built, so we also can create an instance of it by casting our driver to it
        TakesScreenshot takeScreenshot = (TakesScreenshot) DriverUtilities.getDriver();

        // "screenshot" variable is an array of bytes, which compose our screenshot
        byte[] screenshot = takeScreenshot.getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    public static void takeScreenshot(Scenario scenario){
        // we are wrapping the "if" statement in the "try catch" statement, in case if the screenshot taking operation fails,
        // we will catch an exception and print the error message to the console
        try {
            // with this "if" statement we are verifying that the scenario has failed, since we will only need a screenshot takes if the scenario failed
            if (scenario.isFailed()){
                // "screenshot" is the reference variable (Object of the File Class), which stores the actual screenshot
                // ((TakesScreenshot) DriverUtilities.getDriver()).getScreenshotAs(OutputType.FILE) - the TakeScreenshot Interface taking a screenshot and returns it as an OutputType of File
                File screenshot = ((TakesScreenshot) DriverUtilities.getDriver()).getScreenshotAs(OutputType.FILE);
                // try-catch to handle checked exception:
                try {
                    //  .copyFile() method is used here in order to create a copy of the screenshot in the "screenshots" directory: target/automation/screenshots
                    // then in parentheses we are using the String concatenation capability in order to name the File with the scenario name and adding a proper file extension to it .png
                    FileUtils.copyFile(screenshot, new File("target/automation/screenshots" + scenario.getName() + ".png"));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            System.out.println("An error occurred while taking screenshot and cleaning up after the test");
            // e.getMessage(); - if we leave this syntax, the result of this method will be ignored... need to figure out why
            e.printStackTrace();
        }
    }
}

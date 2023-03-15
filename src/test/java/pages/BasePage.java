package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverUtilities;

import java.io.FileWriter;

public class BasePage {

    WebDriver driver = DriverUtilities.getDriver();

    // 1. "dataFaker" is an Object of the Faker Class
    // 2. methods that we will need to use to generate fake data are non-static
    //    therefore we have to use an Object to call those methods, if they were static - we could have called those methods by using the ClassName.methodName();
    // 3. BasePage would be the best place to create an Object of the Fake Class, since all other pages are inheriting (extending) the BasePage
    Faker dataFaker = new Faker();

    FileWriter fileWriter = new FileWriter();

    public BasePage(){
        PageFactory.initElements(driver, this);
    }
}

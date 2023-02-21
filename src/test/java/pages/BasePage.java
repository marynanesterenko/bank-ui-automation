package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverUtilities;

public class BasePage {

    WebDriver driver = DriverUtilities.getDriver();
    Faker dataFaker = new Faker();

    public BasePage(){
        PageFactory.initElements(driver, this);
    }
}

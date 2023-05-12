package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ConfigReader;

public class LoginPage extends BasePage{

    // locators for later
    WebElement invalidCredentialsError;
    @FindBy(xpath = "//span[@aria-hidden='true']")
    WebElement invalidCredentialsErrorExitButton;

    @FindBy (id = "username")
    WebElement usernameInputField;
    @FindBy (id = "password")
    WebElement passwordInputField;
    public void enterValidUserNameAndPassword(String username, String password){
        usernameInputField.sendKeys(ConfigReader.getConfigProperty(username));
        passwordInputField.sendKeys(ConfigReader.getConfigProperty(password));
    }

    @FindBy(id = "submit")
    WebElement signInButton;
    public void clickSignInButton() {
        signInButton.click();
    }

    @FindBy (xpath = "//li[contains(text(), 'Welcome')]")
    WebElement welcomeMessage;
    public void verifyWelcomeMessageIsDisplayed() {
        Assert.assertTrue("UsersPojo is not logged on", welcomeMessage.isDisplayed());
    }
}

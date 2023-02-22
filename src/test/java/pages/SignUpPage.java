package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//*[text()=' Sign Up Here']")
    WebElement signUpHereLink;
    public void verifySignUpHereLinkPresent() {
        Assert.assertTrue("the Sign Up Link is not present on the page", signUpHereLink.isDisplayed());
    }
    public void clickOnSignUpHereLink() {
        signUpHereLink.click();
    }
    public void verifyUserIsOnFirstSignUpPage() {
        String firstSignUpFormLink = driver.getCurrentUrl();
        Assert.assertTrue("The Sign Up here Link did not work", firstSignUpFormLink.equalsIgnoreCase("http://18.116.88.132:8080/bank/signup"));
    }

    @FindBy(className = "login-form")
    WebElement signUpForm1;
    public void verifyFirstSignUpForm() {
        Assert.assertTrue("The first Sign Up form is not displayed", signUpForm1.isDisplayed());
    }

    @FindBy(xpath = "//select")
    WebElement titleDropdown;

    @FindBy(id = "firstName")
    WebElement firstName;
    public void enterFirstName() {
        firstName.sendKeys("Angelina");
    }

    @FindBy(id = "lastName")
    WebElement lastName;
    public void enterLastName() {
        firstName.sendKeys("Jolie");
    }

    @FindBy(xpath = "//strong[text()='Gender']")
    WebElement gender;

    @FindBy(xpath = "//input[@value='F']")
    WebElement femaleOption;
    public void indicateGender() {
        femaleOption.click();
    }

    @FindBy(xpath = "//input[@value='M']")
    WebElement maleOption;

    @FindBy(id = "dob")
    WebElement dob;
    public void enterDateOfBirth() {
        dob.sendKeys("01/01/1991");
    }

    @FindBy(id = "ssn")
    WebElement ssn;
    public void enterSocialSecurityNumber() {
        ssn.sendKeys("456-85-4587");
    }

    @FindBy(id = "emailAddress")
    WebElement email;
    public void enterEmailAddress() {
        email.sendKeys("ajolie@test.com");
    }

    @FindBy(id = "password")
    WebElement password;
    public void enterPassword() {
        password.sendKeys("h)Q+@Nw*xJ(26%7W");
    }

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;
    public void confirmPassword() {
        confirmPassword.sendKeys("h)Q+@Nw*xJ(26%7W");
    }

    @FindBy(xpath = "//button[text()='Next']")
    WebElement nextButton;
    public void clickNextButton() {
        nextButton.click();
    }
}

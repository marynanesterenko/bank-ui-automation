package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    @FindBy(xpath = "//select[@id='title']")
    WebElement titleDropdown;
    public void selectTitle(String title){
        Select selectTitle = new Select(titleDropdown);
        switch (title) {
            case "Mr." -> selectTitle.selectByVisibleText("Mr.");
            case "Ms." -> selectTitle.selectByVisibleText("Ms.");
            case "Mrs." -> selectTitle.selectByVisibleText("Mrs.");
        }
    }

    @FindBy(id = "firstName")
    WebElement firstNameInputField;
    public void enterFirstName(String firstName) {
        firstNameInputField.sendKeys(firstName);
    }

    @FindBy(id = "lastName")
    WebElement lastNameInputField;
    public void enterLastName(String lastName) {
        lastNameInputField.sendKeys(lastName);
    }

    @FindBy(xpath = "//strong[text()='Gender']")
    WebElement genderTitle;
    @FindBy(xpath = "//input[@value='M']")
    WebElement maleOption;
    @FindBy(xpath = "//input[@value='F']")
    WebElement femaleOption;
    public void indicateGender(String gender){
        if (gender.equals("M")){
            maleOption.click();
        } else if (gender.equals("F")){
            femaleOption.click();
        }
    }

    @FindBy(id = "dob")
    WebElement dobInputField;
    public void enterDateOfBirth(String dob) {
        dobInputField.sendKeys(dob);
    }

    @FindBy(id = "ssn")
    WebElement ssnInputField;
    public void enterSocialSecurityNumber(String ssn) {
        ssnInputField.sendKeys(ssn);
    }

    @FindBy(id = "emailAddress")
    WebElement emailInputField;
    public void enterEmailAddress(String email) {
        emailInputField.sendKeys(email);
    }

    @FindBy(id = "password")
    WebElement passwordInputField;
    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;
    public void confirmPassword(String password) {
        confirmPassword.sendKeys(password);
    }

    @FindBy(xpath = "//button[text()='Next']")
    WebElement nextButton;
    public void clickNextButton() {
        nextButton.click();
    }

    public void verifyUserIsOnSecondSignUpPage() {
    }

    public void verifySecondSignUpForm() {
    }

    public void enterStreetAddress(String address) {
    }

    public void enterCity(String locality) {
    }

    public void enterState(String region) {
    }

    public void enterPostalCode(String postalCode) {
    }

    public void enterCountry(String country) {
    }

    public void enterHomePhoneNumber(String homePhone) {
    }

    public void enterCellPhoneNumber(String mobilePhone) {
    }

    public void enterWorkPhoneNumber(String workHome) {
    }

    public void enableTermsAndPolicyCheckBox() {
    }

    public void clickRegisterButton() {
    }

    public void verifyConfirmationMessage() {
    }
}

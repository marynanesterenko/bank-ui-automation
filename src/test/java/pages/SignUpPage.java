package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SignUpPage extends BasePage {

    // locators for later:
    @FindBy(xpath = "//strong[text()='Gender']")
    WebElement genderTitle;
    @FindBy(xpath = "//span[contains(text(),'account is already registered with the email address provided')]")
    WebElement existingEmailErrorMessage;
    @FindBy(xpath = "//span[@aria-hidden='true']")
    WebElement errorMessageExitButton;
    @FindBy(xpath = "//a[text()=' Sign in']")
    WebElement signInLink;

    // locators and methods for now:
    @FindBy(xpath = "//*[text()=' Sign Up Here']")
    WebElement signUpHereLink;
    @FindBy(className = "login-form")
    WebElement signUpForm1;
    @FindBy(xpath = "//select[@id='title']")
    WebElement titleDropdown;
    @FindBy(id = "firstName")
    WebElement firstNameInputField;
    @FindBy(id = "lastName")
    WebElement lastNameInputField;
    @FindBy(xpath = "//input[@value='M']")
    WebElement maleOption;
    @FindBy(xpath = "//input[@value='F']")
    WebElement femaleOption;
    @FindBy(id = "dob")
    WebElement dobInputField;
    @FindBy(id = "ssn")
    WebElement ssnInputField;
    @FindBy(id = "emailAddress")
    WebElement emailInputField;
    @FindBy(id = "password")
    WebElement passwordInputField;
    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;
    @FindBy(xpath = "//button[text()='Next']")
    WebElement nextButton;
    @FindBy(id = "address")
    WebElement addressInputField;
    @FindBy(id = "locality")
    WebElement localityInputField;
    @FindBy(id = "region")
    WebElement regionInputField;
    @FindBy(id = "postalCode")
    WebElement postalCodeInputField;
    @FindBy(id = "country")
    WebElement countryInputField;
    @FindBy(id = "homePhone")
    WebElement homePhoneInputField;
    @FindBy(id = "mobilePhone")
    WebElement mobilePhoneInputField;
    @FindBy(id = "workPhone")
    WebElement workPhoneInputField;
    @FindBy(id = "agree-terms")
    WebElement termsAndPolicyCheckBox;
    @FindBy(xpath = "//button[text()='Register']")
    WebElement registerButton;
    @FindBy(xpath = "//span[text()='Registration Successful. Please Login.']")
    WebElement confirmationMessage;

    public void verifySignUpHereLinkPresent() {
        Assert.assertTrue("the Sign Up Link is not present on the page", signUpHereLink.isDisplayed());
    }
    public void clickOnSignUpHereLink() {
        signUpHereLink.click();
    }
    public void verifyUserIsOnFirstSignUpPage() {
        String firstSignUpFormLink = driver.getCurrentUrl();
        Assert.assertTrue("The Sign Up here Link did not work", firstSignUpFormLink.equalsIgnoreCase("http://18.118.14.155:8080/bank/signup"));
    }

    public void verifyFirstSignUpForm() {
        Assert.assertTrue("The first Sign Up form is not displayed", signUpForm1.isDisplayed());
    }

    public void selectTitle(String title){
        Select selectTitle = new Select(titleDropdown);
        switch (title) {
            case "Mr." -> selectTitle.selectByVisibleText("Mr.");
            case "Ms." -> selectTitle.selectByVisibleText("Ms.");
            case "Mrs." -> selectTitle.selectByVisibleText("Mrs.");
        }
    }

    public void enterFirstName(String firstName) {
        firstNameInputField.sendKeys(dataFaker.name().firstName());
    }

    public void enterLastName(String lastName) {
        lastNameInputField.sendKeys(lastName);
    }

    public void indicateGender(String gender){
        if (gender.equals("M")){
            maleOption.click();
        } else if (gender.equals("F")){
            femaleOption.click();
        }
    }

    public void enterDateOfBirth(String dob) {
        dobInputField.sendKeys(dob);
    }

    public void enterSocialSecurityNumber(String ssn) {
        ssnInputField.sendKeys(ssn);
    }

    public void enterEmailAddress(String email) {
        emailInputField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    public void confirmPassword(String password) {
        confirmPassword.sendKeys(password);
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void enterStreetAddress(String address) {
        addressInputField.sendKeys(address);
    }

    public void enterCity(String locality) {
        localityInputField.sendKeys(locality);
    }

    public void enterState(String region) {
        regionInputField.sendKeys(region);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInputField.sendKeys(postalCode);
    }


    public void enterCountry(String country) {
        countryInputField.sendKeys(country);
    }


    public void enterHomePhoneNumber(String homePhone) {
        homePhoneInputField.sendKeys(homePhone);
    }

    public void enterCellPhoneNumber(String mobilePhone) {
        mobilePhoneInputField.sendKeys(mobilePhone);
    }


    public void enterWorkPhoneNumber(String workHome) {
        workPhoneInputField.sendKeys(workHome);
    }


    public void enableTermsAndPolicyCheckBox() {
        termsAndPolicyCheckBox.click();
    }


    public void clickRegisterButton() {
        registerButton.click();
    }


    public void verifyConfirmationMessage() {
        Assert.assertTrue("Confirmation message is not displayed", confirmationMessage.isDisplayed());
    }

    public void verifyAccountDetailsRecordedInTheDatabase() {
    }
}

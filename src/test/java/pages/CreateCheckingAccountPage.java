package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateCheckingAccountPage extends BasePage{

    @FindBy(xpath = "//*[text()='Create Checking']")
    WebElement createCheckingLabel;
    @FindBy(xpath = "//input[@id='Standard Checking']")
    WebElement standardCheckingRadioBtn;
    @FindBy(xpath = "//input[@id='Interest Checking']")
    WebElement interestCheckingRadioBtn;
    @FindBy(xpath = "//input[@id='Individual']")
    WebElement individualCheckingRadioBtn;
    @FindBy(xpath = "//input[@id='Joint']")
    WebElement jointCheckingRadioBtn;
    @FindBy(xpath = "//input[@id='name']")
    WebElement accountNameInputField;
    @FindBy(xpath = "//input[@id='openingBalance']")
    WebElement initialDepositInputField;
    @FindBy(xpath = "//button[@id='newCheckingSubmit']")
    WebElement submitButton;
    @FindBy(xpath = "//*[text()='Confirmation']")
    WebElement confirmationMessage;

    public void verifyUserIsOnCheckingPage() {
                Assert.assertTrue("use is not on the create checking page", createCheckingLabel.isDisplayed());
    }

    public void selectAccountType(String accountType) {
        if (accountType.equalsIgnoreCase("Standard Checking")){
            standardCheckingRadioBtn.click();
        } else if (accountType.equalsIgnoreCase("Interest Checking")){
            interestCheckingRadioBtn.click();
        }
    }

    public void selectAccountOwnership(String accountOwnership) {
        if (accountOwnership.equalsIgnoreCase("Individual")){
            individualCheckingRadioBtn.click();
        } else if (accountOwnership.equalsIgnoreCase("Joint")){
            jointCheckingRadioBtn.click();
        }
    }

    public void enterAccountName(String accountName) {
        accountNameInputField.sendKeys(accountName);
    }

    public void enterInitialDeposit(String initialDeposit) {
        initialDepositInputField.sendKeys(initialDeposit);
    }

    public void clickSubmitBtn() {
        submitButton.click();
    }

    public void verifyCheckingAccountWasCreated (){
        System.out.println(confirmationMessage.getText()); // this line is just for us to use
        Assert.assertTrue("Confirmation message is not displayed", confirmationMessage.isDisplayed());
    }
}

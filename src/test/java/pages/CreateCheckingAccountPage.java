package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateCheckingAccountPage extends BasePage{

    @FindBy(xpath = "//*[text()='Create Checking']")
    WebElement createCheckingLabel;
    public void verifyUserIsOnCheckingPage() {
        Assert.assertTrue("use id not on the create checking page", createCheckingLabel.isDisplayed());
    }

    @FindBy(xpath = "//input[@id='Standard Checking']")
    WebElement standardCheckingRadioBtn;
    @FindBy(xpath = "//input[@id='Interest Checking']")
    WebElement interestCheckingRadioBtn;
    public void selectAccountType(String accountType) {
        if (accountType.equalsIgnoreCase("Standard Checking")){
            standardCheckingRadioBtn.click();
        } else if (accountType.equalsIgnoreCase("Interest Checking")){
            interestCheckingRadioBtn.click();
        }
    }

    @FindBy(xpath = "//input[@id='Individual']")
    WebElement individualCheckingRadioBtn;
    @FindBy(xpath = "//input[@id='Joint']")
    WebElement jointCheckingRadioBtn;
    public void selectAccountOwnership(String accountOwnership) {
        if (accountOwnership.equalsIgnoreCase("Individual")){
            individualCheckingRadioBtn.click();
        } else if (accountOwnership.equalsIgnoreCase("Joint")){
            jointCheckingRadioBtn.click();
        }
    }

    @FindBy(xpath = "//input[@id='name']")
    WebElement accountNameInputField;
    public void enterAccountName(String accountName) {
        accountNameInputField.sendKeys(accountName);
    }

    @FindBy(xpath = "//input[@id='openingBalance']")
    WebElement initialDepositInputField;
    public void enterInitialDeposit(String initialDeposit) {
        initialDepositInputField.sendKeys(initialDeposit);
    }

    @FindBy(xpath = "//button[@id='newCheckingSubmit']")
    WebElement submitButton;
    public void clickSubmitBtn() {
        submitButton.click();
    }
}

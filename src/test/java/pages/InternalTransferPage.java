package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;

public class InternalTransferPage extends BasePage{

    @FindBy(xpath = "//*[text()='Internal Transfer']")
    WebElement internalTransferLabel;
    @FindBy(id = "fromAccount")
    WebElement fromAccountDropdown;
    @FindBy(id = "toAccount")
    WebElement toAccountDropdown;
    @FindBy(id = "amount")
    WebElement amountInputField;
    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    WebElement submitBtn;

    public void verifyInternalTransferPage() {
        Assert.assertTrue("User is not on the Internal Transfer Page", internalTransferLabel.isDisplayed());
    }

    public void selectFromAccount(String fromAccount) {
        Select select = new Select(fromAccountDropdown);
        select.selectByVisibleText(ConfigReader.getConfigProperty(fromAccount));
    }

    public void selectToAccount(String toAccount) {
        Select select = new Select(toAccountDropdown);
        select.selectByVisibleText(ConfigReader.getConfigProperty(toAccount));
    }

    public void enterAmount(String amount) {
        amountInputField.sendKeys(ConfigReader.getConfigProperty(amount));
    }

    public void clickSubmitMethod() {
        submitBtn.click();
    }
}

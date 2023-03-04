package pages;

import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;

public class InternalTransferPage {

    public void verifyInternalTransferPage() {
        Assert.assertTrue("From Account dropdown is not visible", fromAccountDropdown.isDisplayed());
        Assert.assertTrue("To Account Dropdown is not visible", toAccountDropdown.isDisplayed());
    }

    @FindBy(id = "fromAccount")
    WebElement fromAccountDropdown;
    public void selectFromAccount(String fromAccount) {
        Select select = new Select(fromAccountDropdown);
        select.selectByVisibleText(ConfigReader.getConfigProperty(fromAccount));
    }

    @FindBy(id = "toAccount")
    WebElement toAccountDropdown;
    public void selectToAccount(String toAccount) {
        Select select = new Select(toAccountDropdown);
        select.selectByVisibleText(ConfigReader.getConfigProperty(toAccount));
    }

    @FindBy(id = "amount")
    WebElement amountInputField;
    public void enterAmount(String amount) {
        amountInputField.sendKeys(ConfigReader.getConfigProperty(amount));
    }

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    WebElement submitBtn;
    public void clickSubmitMethod() {
        submitBtn.click();
    }
}

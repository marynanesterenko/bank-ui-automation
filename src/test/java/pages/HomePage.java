package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//li[@class='active']")
    WebElement welcomeTestMessage;
    public void verifyPage(){
        Assert.assertTrue("Welcome test message not visible could be on wrong page", welcomeTestMessage.isDisplayed());
    }

    @FindBy(xpath = "//a[@id='transfer-menu-item']")
    WebElement transferItemMenu;
    public void clickOnTransferBetweenAccounts() {
        transferItemMenu.click();
    }

    @FindBy(xpath = "//a[@id='checking-menu']")
    WebElement checkingDropdown;
    public void clickOnCheckingDropDownMenu() {
        checkingDropdown.click();
    }

    @FindBy(xpath = "//a[@id='view-checking-menu-item']")
    WebElement newCheckingOption;
    public void clickOnNewCheckingOption() {
        newCheckingOption.click();
    }
}

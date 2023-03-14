package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//li[@class='active']")
    WebElement welcomeTestMessage;

    @FindBy(xpath = "//a[@id='transfer-menu-item']")
    WebElement transferItemMenu;

    @FindBy(xpath = "//a[@id='checking-menu']")
    WebElement checkingDropdown;

    @FindBy(xpath = "//*[text()='New Checking']")
    WebElement newCheckingOption;

    @FindBy(id = "transfer-menu-item")
    WebElement transferBetweenAccountsMenuOption;

    public void verifyPage(){
        Assert.assertTrue("Welcome test message not visible could be on wrong page", welcomeTestMessage.isDisplayed());
    }

    public void clickOnTransferBetweenAccounts() {
        transferItemMenu.click();
    }

    public void clickOnCheckingDropDownMenu() {

        checkingDropdown.click();
    }

    public void clickOnNewCheckingOption() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newCheckingOption.click();
    }

    public void clickOnTransferBetweenAccountsMenuOption(){
        transferBetweenAccountsMenuOption.click();
    }


}

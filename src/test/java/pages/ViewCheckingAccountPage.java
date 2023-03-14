package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ConfigReader;
import utilities.DBUtils;

public class ViewCheckingAccountPage extends BasePage{

    @FindBy(xpath = "//*[text()='View Checking Accounts']")
    WebElement viewCheckingAccountPageTitle;
    @FindBy(xpath = "//*[@id='transactionTable']//tbody/tr[1]/td[4]")
    WebElement transactionAmount;

    public void verifyUserIsOnViewCheckingAccountPage(){
        Assert.assertTrue("User is not of the View Checking Accounts Page", viewCheckingAccountPageTitle.isDisplayed());
    }

    public void verifyTransactionHistoryIsDisplayed(){
        String msg = "Transfer amount on table is not matching with actual transfer amount";
        String expected = transactionAmount.getText();
        String actual = "$" + ConfigReader.getConfigProperty("transfer.amount");
        Assert.assertEquals(msg,expected,actual);

        //Assert.assertTrue("Transfer amount on table is not matching with actual transfer amount",transactionAmount.getText().contains(ConfigReader.getConfigProperty("transfer.amount")));
    }
}

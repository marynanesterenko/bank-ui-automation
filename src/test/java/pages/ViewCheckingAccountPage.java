package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.DBUtils;

public class ViewCheckingAccountPage {

    @FindBy(xpath = "//span[@id='new-account-msg]")
    WebElement confirmationMessage;
    public void verifyCheckingAccountIsCreated() {
        Assert.assertTrue("user is not able to see a confirmation message", confirmationMessage.isDisplayed());
    }


    public void verifyCheckingAccountRecordInDatabase() {
        DBUtils.executeSQLQuery("select * from digitalbank.account where name = 'Primary Checking';");
    }

}

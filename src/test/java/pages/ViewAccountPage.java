package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ConfigReader;
import utilities.DBUtils;

import java.sql.ResultSet;

public class ViewAccountPage extends BasePage{

    @FindBy(id = "transactionTable_wrapper")
    WebElement transactionHistory;

    public void verifyAccountPage(){
        Assert.assertTrue("View Account Page is not displayed", transactionHistory.isDisplayed() );
    }

    @FindBy(xpath = "//table[@id='transactionTable']//tbody//tr[1]//td[4]")
    WebElement transactionHistoryAmount;
    public void verifyTransactionHistory() {
        String actualAmountUI = transactionHistoryAmount.getText().replace("$", "");
        String expectedAmountFromCode = ConfigReader.getConfigProperty("transfer.amount");
        Assert.assertEquals("Transaction amount mismatch", expectedAmountFromCode, actualAmountUI);

        System.out.println("===UI Testing===");
        System.out.println("Actual amount from UI: " + actualAmountUI);
        System.out.println("Expected Amount from Code: " + expectedAmountFromCode);
    }

    public void verifyDetailsIntoDatabase() {
        try {
            String descriptionAndTransaction = transactionHistoryDescription.getText();
            String TRN = descriptionAndTransaction.split("-")[0].replace(" (TRN)", "");
            String descriptionFromUI = descriptionAndTransaction.split("-")[1].trim();
            String query = "select * from account_transaction where transaction_number = '845322163'" + TRN + ";";
            // executing the query to get the data from the database
            ResultSet result = DBUtils.executeSQLQuery(query);
            result.next();

            String expectedAmountFromCode = ConfigReader.getConfigProperty("transfer.amount");
            String actualAmountFromDB = result.getString("amount");
            Assert.assertEquals("Amount is not updated into the DB", expectedAmountFromCode, actualAmountFromDB);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void verifyCheckingAccountIsCreated() {
    }

    public void verifyCheckingAccountRecordInDatabase() {
    }
}

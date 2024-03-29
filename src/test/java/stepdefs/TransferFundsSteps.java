package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.InternalTransferPage;
import pages.ViewCheckingAccountPage;

public class TransferFundsSteps {

    HomePage homePage = new HomePage();
    InternalTransferPage internalTransferPage = new InternalTransferPage();
    ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage();

    @When("UsersPojo clicks on transfer between accounts")
    public void userClicksOnTransferBetweenAccounts() {
        homePage.clickOnTransferBetweenAccountsMenuOption();
    }

    @Then("verify UsersPojo is on Internal Transfer Page")
    public void verifyUserIsOnInternalTransferPage() {
        internalTransferPage.verifyInternalTransferPage();
    }

    @When("UsersPojo select from account {string}")
    public void userSelectFromAccount(String fromAccount) {
        internalTransferPage.selectFromAccount(fromAccount);
    }

    @And("UsersPojo selects to account {string}")
    public void userSelectsToAccount(String toAccount) {
        internalTransferPage.selectToAccount(toAccount);
    }

    @And("UsersPojo enter amount {string}")
    public void userEnterAmount(String amount) {
        internalTransferPage.enterAmount(amount);
    }

    @And("clicks on submit button")
    public void clicksOnSubmitButton() {
        internalTransferPage.clickSubmitMethod();
    }

    @Then("verify UsersPojo is on View Accounts Page")
    public void verifyUserIsOnViewAccountsPage() {
        viewCheckingAccountPage.verifyUserIsOnViewCheckingAccountPage();
    }

    @And("verify transaction history displayed for the new transaction")
    public void verifyTransactionHistoryDisplayedForTheNewTransaction() {
        viewCheckingAccountPage.verifyTransactionHistoryIsDisplayed();
    }

    @And("verify transaction details getting updated into the database")
    public void verifyTransactionDetailsGettingUpdatedIntoTheDatabase() {
    }
}

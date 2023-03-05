package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomeSteps {

    HomePage homePage = new HomePage();

    @Given("user is on home page")
    public void user_is_on_home_page() {
        homePage.verifyPage();
    }

    @When("user clicks on checking dropdown menu")
    public void user_clicks_on_checking_dropdown_menu() {
    homePage.clickOnCheckingDropDownMenu();
    }
    @When("user clicks on New Checking option")
    public void user_clicks_on_new_checking_option() {
        homePage.clickOnNewCheckingOption();

    }

    @When("user clicks on transfer between accounts")
    public void user_clicks_on_transfer_between_accounts() {
        homePage.verifyPage();
    }

    @Then("verify user is on View Accounts Page")
    public void verify_user_is_on_view_accounts_page() {
        homePage.clickOnTransferBetweenAccounts();
    }

//    @Then("verify transaction history displayed for the new transaction")
//    public void verify_transaction_history_displayed_for_the_new_transaction() {
//        homePage.verifyTransactionHistoryDisplayed();
//    }
//
//    @Then("verify transaction details getting updated into the database")
//    public void verify_transaction_details_getting_updated_into_the_database() {
//        homePage.verifyTransactionDetailsGettingUpdatedIntoTheDatabase();
//    }
}

package stepdefs;

import io.cucumber.java.en.*;
import pages.CreateCheckingAccountPage;

public class CreateCheckingAccountSteps {

    CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage();

    @Then("verify user is on Create Checking Page")
    public void verify_user_is_on_create_checking_page() {
        createCheckingAccountPage.verifyUserIsOnCheckingPage();
        
    }
    @When("user selects {string} on Account type")
    public void user_selects_on_account_type(String accountType) {
        createCheckingAccountPage.selectAccountType(accountType);
    }
    @When("user selects {string} on Account ownership")
    public void user_selects_on_account_ownership(String accountOwnership) {
        createCheckingAccountPage.selectAccountOwnership(accountOwnership);
    }
    @When("user enters {string} on Account Name field")
    public void user_enters_on_account_name_field(String accountName) {
       createCheckingAccountPage.enterAccountName(accountName);
    }
    @When("user enters {string} on Initial Deposit field")
    public void user_enters_on_initial_deposit_field(String initialDeposit) {
        createCheckingAccountPage.enterInitialDeposit(initialDeposit);
    }
    @When("user clicks Submit button")
    public void user_clicks_submit_button() {
        createCheckingAccountPage.clickSubmitBtn();
    }
}

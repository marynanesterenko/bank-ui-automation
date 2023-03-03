package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InternalTransferPage;

public class InternalTransferSteps {

    InternalTransferPage internalTransferPage = new InternalTransferPage();

    @Then("verify user is on Internal Transfer Page")
    public void verify_user_is_on_internal_transfer_page() {
        internalTransferPage.verifyInternalTransferPage();
    }

    @When("User select from account {string}")
    public void user_select_from_account(String fromAccount) {
        internalTransferPage.selectFromAccount(fromAccount);
    }

    @When("user selects to account {string}")
    public void user_selects_to_account(String toAccount) {
        internalTransferPage.selectToAccount(toAccount);
    }

    @When("user enter amount {string}")
    public void user_enter_amount(String amount) {
        internalTransferPage.enterAmount(amount);
    }

    @When("clicks on submit button")
    public void clicks_on_submit_button() {
        internalTransferPage.clickSubmitMethod();
    }
}

package stepdefs;

import io.cucumber.java.en.*;
import pages.CreateCheckingAccountPage;
import pages.HomePage;

public class CreateNewCheckingAccountSteps {

    HomePage homePage = new HomePage();
    CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage();

    @When("UsersPojo clicks on the checking drop down menu")
    public void userClicksOnTheCheckingDropDownMenu() {
        homePage.clickOnCheckingDropDownMenu();
    }

    @And("UsersPojo clicks on the New Checking option")
    public void userClicksOnTheNewCheckingOption() {
        homePage.clickOnNewCheckingOption();
    }

    @Then("UsersPojo is on create checking page")
    public void userIsOnCreateCheckingPage() {
        createCheckingAccountPage.verifyUserIsOnCheckingPage();
    }

    @When("UsersPojo selects {string} on Account Type field")
    public void userSelectsOnAccountTypeField(String accountType) {
        createCheckingAccountPage.selectAccountType(accountType);
    }

    @And("UsersPojo selects {string} on Account Ownership field")
    public void userSelectsOnAccountOwnershipField(String accountOwnership) {
        createCheckingAccountPage.selectAccountOwnership(accountOwnership);
    }

    @And("UsersPojo enters {string} on Account Name field")
    public void userEntersOnAccountNameField(String accountName) {
        createCheckingAccountPage.enterAccountName(accountName);
    }

    @And("UsersPojo enters {string}  on Initial Deposit field")
    public void userEntersOnInitialDepositField(String initialDeposit) {
        createCheckingAccountPage.enterInitialDeposit(initialDeposit);
    }

    @And("UsersPojo clicks Submit button")
    public void userClicksSubmitButton() {
        createCheckingAccountPage.clickSubmitBtn();
    }

    @Then("verify the Account was successfully created")
    public void verifyTheAccountWasSuccessfullyCreated() {
        createCheckingAccountPage.verifyCheckingAccountWasCreated();
    }
}

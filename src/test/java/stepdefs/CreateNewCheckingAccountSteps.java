package stepdefs;

import io.cucumber.java.en.*;
import pages.CreateCheckingAccountPage;
import pages.HomePage;

public class CreateNewCheckingAccountSteps {

    HomePage homePage = new HomePage();
    CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage();

    @When("user clicks on the checking drop down menu")
    public void userClicksOnTheCheckingDropDownMenu() {
        homePage.clickOnCheckingDropDownMenu();
    }

    @And("user clicks on the New Checking option")
    public void userClicksOnTheNewCheckingOption() {
        homePage.clickOnNewCheckingOption();
    }

    @Then("user is on create checking page")
    public void userIsOnCreateCheckingPage() {
        createCheckingAccountPage.verifyUserIsOnCheckingPage();
    }

    @When("user selects {string} on Account Type field")
    public void userSelectsOnAccountTypeField(String accountType) {
        createCheckingAccountPage.selectAccountType(accountType);
    }

    @And("user selects {string} on Account Ownership field")
    public void userSelectsOnAccountOwnershipField(String accountOwnership) {
        createCheckingAccountPage.selectAccountOwnership(accountOwnership);
    }

    @And("user enters {string} on Account Name field")
    public void userEntersOnAccountNameField(String accountName) {
        createCheckingAccountPage.enterAccountName(accountName);
    }

    @And("user enters {string}  on Initial Deposit field")
    public void userEntersOnInitialDepositField(String initialDeposit) {
        createCheckingAccountPage.enterInitialDeposit(initialDeposit);
    }

    @And("user clicks Submit button")
    public void userClicksSubmitButton() {
        createCheckingAccountPage.clickSubmitBtn();
    }

    @Then("verify the Account was successfully created")
    public void verifyTheAccountWasSuccessfullyCreated() {
        createCheckingAccountPage.verifyCheckingAccountWasCreated();
    }

//    @Then("verify the Account was successfully created")
//    public void verifyTheAccountWasSuccessfullyCreated() {
//    }
}

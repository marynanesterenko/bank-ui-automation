package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @When("UsersPojo enters a combination of valid {string} and valid {string}")
    public void user_enters_a_combination_of_valid_and_valid(String username, String password) {
        loginPage.enterValidUserNameAndPassword(username, password);
    }

    @When("clicks Sign In button")
    public void clicks_sign_in_button() {
        loginPage.clickSignInButton();
    }

    @Then("UsersPojo is logged on and welcome message is displayed in the right upper corner")
    public void user_is_logged_on_and_welcome_message_is_displayed_in_the_right_upper_corner() {
        loginPage.verifyWelcomeMessageIsDisplayed();
    }
}

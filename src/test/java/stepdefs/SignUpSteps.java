package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ro.Si;
import pages.SignUpPage;

public class SignUpSteps {

    SignUpPage signUpPage = new SignUpPage();

    @Given("user navigates to Digital Bank Login Page")
    public void user_navigates_to_digital_bank_login_page() {

    }

    @Given("the Sign Up here link is present on the page")
    public void the_sign_up_here_link_is_present_on_the_page() {
        signUpPage.verifySignUpHereLinkPresent();
    }

    @When("user clicks on the Sign Up Here link")
    public void user_clicks_on_the_sign_up_here_link() {
        signUpPage.clickOnSignUpHereLink();
    }

    @Then("user is transferred to the first Sign Up form to initiate the Sign Up process")
    public void user_is_transferred_to_the_first_sign_up_form_to_initiate_the_sign_up_process() {
        signUpPage.verifyUserIsOnFirstSignUpPage();
    }

    @Given("user is on the first Sign Up form")
    public void user_is_on_the_first_sign_up_form() {
        
    }

    @When("user fills out all the input fields")
    public void user_fills_out_all_the_input_fields() {
        
    }

    @When("clicks NEXT button")
    public void clicks_next_button() {
        
    }

    @Then("user is transferred to the second Sign Up form to finish the Sign Up process")
    public void user_is_transferred_to_the_second_sign_up_form_to_finish_the_sign_up_process() {
        
    }

    @Given("user is on the second Sign Up form")
    public void user_is_on_the_second_sign_up_form() {
        
    }

    @When("enables the Agree the terms and policy check box")
    public void enables_the_agree_the_terms_and_policy_check_box() {
        
    }

    @When("clicks REGISTER button")
    public void clicks_register_button() {
        
    }

    @Then("the confirmation message about successful account creation is displayed")
    public void the_confirmation_message_about_successful_account_creation_is_displayed() {
        
    }

}

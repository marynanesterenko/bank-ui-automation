package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ro.Si;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.SignUpPage;

public class SignUpSteps {

    SignUpPage signUpPage = new SignUpPage();

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
        signUpPage.verifyFirstSignUpForm();
    }

    @When("user selects their preferred {string} from the Title dropdown")
    public void user_selects_their_preferred_from_the_title_dropdown(String title) {
        signUpPage.selectTitle(title);
    }

    @When("user enters their {string} in the First Name input field")
    public void user_enters_their_in_the_first_name_input_field(String firstName) {
        signUpPage.enterFirstName(firstName);
    }

    @When("user enters their {string} in the Last Name input field")
    public void user_enters_their_in_the_last_name_input_field(String lastName) {
        signUpPage.enterLastName(lastName);
    }

    @When("user indicates their {string} in the Gender field")
    public void user_indicates_their_in_the_gender_field(String gender) {
        signUpPage.indicateGender(gender);
    }

    @When("user enters their {string} in Date Of Birth input field")
    public void user_enters_their_in_date_of_birth_input_field(String dob) {
        signUpPage.enterDateOfBirth(dob);
    }

    @When("user enters their {string} in the Social Security Number input field")
    public void user_enters_their_in_the_social_security_number_input_field(String ssn) {
        signUpPage.enterSocialSecurityNumber(ssn);
    }

    @When("user enters their {string} in the Email Address input field")
    public void user_enters_their_in_the_email_address_input_field(String email) {
        signUpPage.enterEmailAddress(email);
    }

    @When("user creates and enters their {string} in the Password input field")
    public void user_creates_and_enters_their_in_the_password_input_field(String password) {
        signUpPage.enterPassword(password);
    }

    @When("user re-enters their {string} in the Confirm Password input field")
    public void user_re_enters_their_in_the_confirm_password_input_field(String password) {
        signUpPage.confirmPassword(password);
    }

    @When("user clicks next button")
    public void user_clicks_next_button() {
        signUpPage.clickNextButton();
    }

    @Then("user is transferred to the second Sign Up form to finish the Sign Up process")
    public void user_is_transferred_to_the_second_sign_up_form_to_finish_the_sign_up_process() {
        
    }

    @Given("user is on the second Sign Up form")
    public void user_is_on_the_second_sign_up_form() {
        
    }

    @Given("user enters their {string} in the Address input field")
    public void user_enters_their_in_the_address_input_field(String address) {
        
    }

    @Given("user enters their {string} in the Locality input field")
    public void user_enters_their_in_the_locality_input_field(String locality) {
        
    }

    @Given("user enters their {string} in the Region input field")
    public void user_enters_their_in_the_region_input_field(String region) {
        
    }

    @Given("user enters their {string} in the Postal Code input field")
    public void user_enters_their_in_the_postal_code_input_field(String postalCode) {
        
    }

    @Given("user enters their {string} in the Country input field")
    public void user_enters_their_in_the_country_input_field(String country) {
        
    }

    @Given("user enters their {string} in the Home Phone input field")
    public void user_enters_their_in_the_home_phone_input_field(String homePhone) {
        
    }

    @Given("user enters their {string} in the Mobile Phone input field")
    public void user_enters_their_in_the_mobile_phone_input_field(String mobileHome) {
        
    }

    @Given("user enters their {string} in the Work Phone input field")
    public void user_enters_their_in_the_work_phone_input_field(String workHome) {
        
    }

    @Given("user enables the Agree the terms and policy check box")
    public void user_enables_the_agree_the_terms_and_policy_check_box() {
        
    }

    @Given("user clicks register button")
    public void user_clicks_register_button() {
        
    }

    @Then("the confirmation message about successful account creation is displayed")
    public void the_confirmation_message_about_successful_account_creation_is_displayed() {
        
    }
}



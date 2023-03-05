Feature: sign up

  Background:
    Given user navigates to Digital Bank Login Page

  Scenario: verify user is able to access Sign Up Page
    Given the Sign Up here link is present on the page
    When user clicks on the Sign Up Here link
    Then user is transferred to the first Sign Up form to initiate the Sign Up process

  Scenario: verify user is able to complete the fist part of the sign up process
    Given user is on the first Sign Up form
    When user selects their preferred "<title>" from the Title dropdown
    And user enters their "<first name>" in the First Name input field
    And user enters their "<last name>" in the Last Name input field
    And user indicates their "<gender>" in the Gender field
    And user enters their "<dob>" in Date Of Birth input field
    And user enters their "<ssn>" in the Social Security Number input field
    And user enters their "<email>" in the Email Address input field
    And user creates and enters their "<password>" in the Password input field
    And user re-enters their "<password>" in the Confirm Password input field
    And user clicks next button
    Then user is transferred to the second Sign Up form to finish the Sign Up process

  Scenario: verify user is able to complete the second part of the Sign up process
    Given user is on the second Sign Up form
    And user enters their "<address>" in the Address input field
    And user enters their "<locality>" in the Locality input field
    And user enters their "<region>" in the Region input field
    And user enters their "<postal code>" in the Postal Code input field
    And user enters their "<country>" in the Country input field
    And user enters their "<home phone>" in the Home Phone input field
    And user enters their "<mobile phone>" in the Mobile Phone input field
    And user enters their "<work phone>" in the Work Phone input field
    And user enables the Agree the terms and policy check box
    And user clicks register button
    Then the confirmation message about successful account creation is displayed
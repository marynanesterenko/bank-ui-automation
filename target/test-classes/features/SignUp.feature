Feature: sign up

  Background:
    Given user navigates to Digital Bank Login Page

  Scenario: verify user is able to access Sign Up Page
    Given the Sign Up here link is present on the page
    When user clicks on the Sign Up Here link
    Then user is transferred to the first Sign Up form to initiate the Sign Up process

  Scenario: verify user is able to complete the fist part of the sign up process
    Given user is on the first Sign Up form
    When user fills out all the input fields
    And clicks NEXT button
    Then user is transferred to the second Sign Up form to finish the Sign Up process

  Scenario: verify user is able to complete the second part of the Sign up process
    Given user is on the second Sign Up form
    When user fills out all the input fields
    And enables the Agree the terms and policy check box
    And clicks REGISTER button
    Then the confirmation message about successful account creation is displayed
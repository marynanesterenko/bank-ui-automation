Feature: sign up
  Background:
    Given user navigates to Digital Bank Login Page
    And Sign Up Here link is visible

    Scenario: verify user is able to access Sign Up Page
      When user clicks on the Sign Up Here link
      Then user is transferred to the first Sign Up form to initiate the Sign Up process

      Scenario: verify user is able to complete the fist part of the sign up process
        When user fills out all the input fields
        And clicks NEXT button
        Then user is transferred to the second Sign Up form to finish the Sign Up process

        Scenario: verify user is able to complete the second part of the Sign up process
          When user fills out all the input fields
          And enables the Agree the terms and policy check box
          And clicks REGISTER button
          Then the confirmation message about successful account creation is displayed
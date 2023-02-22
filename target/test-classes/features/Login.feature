Feature: login

  Background:
    Given user navigates to Digital Bank Login Page

  Scenario: verify user is able to log in with valid credentials
    When user enters valid username and valid password
    And clicks SIGN IN button
    Then user is logged on and welcome message is displayed in the right upper corner
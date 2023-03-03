Feature: Transfer Funds Feature

  Background:
    Given user navigates to Digital Bank Login Page

  Scenario: verify user is able to log in with valid credentials
    When user enters valid username and valid password
    And clicks SIGN IN button
    Then user is logged on and welcome message is displayed in the right upper corner

  Scenario: verify user is able to transfer funds between acc
    When user clicks on transfer between accounts

    Then verify user is on Internal Transfer Page
    When User select from account "transfer.from.account"
    And  user selects to account "transfer.to.account"
    And user enter amount "transfer.amount"
    And clicks on submit button

    Then verify user is on View Accounts Page
    And verify transaction history displayed for the new transaction
    And verify transaction details getting updated into the database

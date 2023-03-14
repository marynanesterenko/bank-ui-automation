Feature: Transfer Funds Feature

  Background: verify user is able to log in with valid credentials
    When user enters a combination of valid "valid.username" and valid "valid.password"
    And clicks Sign In button
    Then user is logged on and welcome message is displayed in the right upper corner

  Scenario: verify user is able to transfer funds between accounts
    When user clicks on transfer between accounts
    Then verify user is on Internal Transfer Page
    When User select from account "transfer.from.account"
    And  user selects to account "transfer.to.account"
    And user enter amount "transfer.amount"
    And clicks on submit button
    Then verify user is on View Accounts Page
    And verify transaction history displayed for the new transaction
    And verify transaction details getting updated into the database

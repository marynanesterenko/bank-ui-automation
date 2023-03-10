Feature: Account creation feature

  Background: verify user is able to log in with valid credentials
    When user enters a combination of valid "valid.username" and valid "valid.password"
    And clicks Sign In button
    Then user is logged on and welcome message is displayed in the right upper corner

    Scenario Outline: verify user is able to create new checking account
      When user clicks on the checking drop down menu
      And user clicks on the New Checking option
      Then user is on create checking page
      When user selects "<Checking Account Type>" on Account Type field
      And user selects "<Account Ownership Type>" on Account Ownership field
      And user enters "<Account Name>" on Account Name field
      And user enters "<Initial Deposit Amount>"  on Initial Deposit field
      And user clicks Submit button
      Then verify the Account was successfully created
#      Then verify account record is created into a database

      Examples:
        | Checking Account Type | Account Ownership Type | Account Name       | Initial Deposit Amount |
        | Standard Checking     | Individual             | Secondary Checking | 10000                  |

Feature: Account creation feature

  Background: verify user is able to log in with valid credentials
    When user enters valid username and valid password
    And clicks SIGN IN button
    Then user is logged on and welcome message is displayed in the right upper corner

    Scenario Outline: verify user is able to create checking account
      Given user is on the Home Page
      When user clicks on the checking drop down menu
      And user clicks on the New Checking option
      Then user is on create checking page

      When user selects "<Checking Account Type>" on Account Type field
      And user selects "<Account Ownership Type>" on Account Ownership field
      And user enters "<Account Name" on Account Name field
      And user enters "<Initial Deposit Amount>"  on Initial Deposit field
      And user clicks Submit button

      Then verify the Account was successfully created
      Then verify account record is created into a database

      Examples:
      | Checking Account Type | Account Ownership Type | Account Name     | Initial Deposit Amount |
      | Standard Checking     | Individual             | Primary Checking | 5000                   |

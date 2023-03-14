Feature: login

  Scenario: verify user is able to log in with valid credentials
    When user enters a combination of valid "valid.username" and valid "valid.password"
    And clicks Sign In button
    Then user is logged on and welcome message is displayed in the right upper corner

# !REMEMBER: the <> are for the Scenario Outline specifically

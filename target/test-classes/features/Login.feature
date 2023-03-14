Feature: login

  Scenario Outline: verify user is able to log in with valid credentials
    When user enters a combination of valid "<valid username>" and valid "<valid password>"
    And clicks Sign In button
    Then user is logged on and welcome message is displayed in the right upper corner

    Examples:
      | valid username     | valid password   |
      | ajolie@test.com    | h)Q+@Nw*xJ(26%7W |

#      | m.edwards@test.com | F24#9JCWQCDq42&D |
#      | b.morales@test.com | wtY7MVkKxd!wX+*L |
#      | a.roberts@test.com | 3+EyPJL3dyJytYeC |


Feature: sign up

  Scenario Outline: verify user is able to access Sign Up Page and is able to complete the fist part of the sign up process
    Given the Sign Up here link is present on the page
    When user clicks on the Sign Up Here link
    Then user is transferred to the first Sign Up form to initiate the Sign Up process
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
#    Then verify user account record is created in the database

    Examples:
      | title | first name | last name | gender | dob        | ssn         | email              | password         | address              | locality   | region | postal code | country | home phone     | mobile phone  | work phone     |
      | Mr.   | Matthew    | Edwards   | M      | 01/01/1991 | 526-58-4562 | m.edwards@test.com | F24#9JCWQCDq42&D | 3708 Pearl Street    | Sacramento | CA     | 95823       | USA     | (916) 392-0425 | (916)506-9026 | (916) 616-8836 |
#      | Ms.   | Beverly    | Morales   | F      | 01/02/1992 | 451-52-6325 | b.morales@test.com | wtY7MVkKxd!wX+*L | 4643 Fairmont Avenue | Madison    | WI     | 19714       | USA     | (920) 756-1831 | (920)588-4521 | (785) 935-6620 |
#      | Mrs.  | Anna       | Roberts   | F      | 01/03/1993 | 897-12-3625 | a.roberts@test.com | 3+EyPJL3dyJytYeC | 4695 Callison Lane   | Brillion   | VA     | 23462       | USA     | (897) 12-3625  | (916)528-9482 | (620) 238-7818 |
















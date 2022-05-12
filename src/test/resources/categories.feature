Feature: Category
  As a user
  I want to input and get category
  So that i can access input category and get category

# Get Categories (BDD)
# (+)
  Scenario: As a user i want to see all categories
    Given I set an endpoint for user
    When I request GET category list
    Then I validate the status code is 200
    And validate the data detail after get category

# Create Categories (DDT)
# (+)(-)
  Scenario Outline: Category Functionality
    Given I set an endpoint for user
    When I request "<input>" POST category
    Then I validate the status code is "<sCode>"
    And validate the data detail after "<status>" category
    Examples:
      | input | sCode | status |
      | validInput | 200 | inputCreate       |
      | inputNumber| 400 | inputFailedNumber |
      | inputNull  | 500 | inputFailedNull   |



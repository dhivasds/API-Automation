#Feature: Category
#
## Get Categories
## (+)
#Scenario: As a user i want to see all categories
#  Given I set an endpoint for user
#  When I request GET category list
#  Then I validate the status code is 200
#  And validate the data detail after get category
#
## Create Categories
## (+)(-)
#  Scenario Outline: Category
#    Given I set an endpoint for user
#    When I request "<input>" POST category
#    Then I validate the status code is "<sCode>"
#    And validate the data detail after "<status>" category
#    Examples:
#      | input | sCode | status |
#      | validInput | 200 | inputCreate |
#      | inputNumber| 400 | inputFailedNumber |
#      | inputNull  | 500 | inputFailedNull   |
#
##  inputNull = else jadi bebas diksh nama apa aja
#
##  Scenario: As a user i want to add categories
##    Given I set an endpoint for user
##    When I request POST category
##    Then I validate the status code is 200
##    And validate the data detail after create category
##
##  Scenario: As a user i want failed to add categories
##    Given I set an endpoint for user
##    When I request POST category
##    Then I validate the status code is 400
##    And validate the data detail after failed create category
Feature: Category


Scenario: As a user i want to see all categories
  Given I set an endpoint for user
  When I request GET category list
  Then I validate the status code is 200
  And validate the data detail after get category
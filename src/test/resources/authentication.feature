Feature: Authentication
  As a user


  Scenario Outline: authentication
    Given I set an "<endpoint>" for authentication
    When I request "<input>" POST authentication
    Then I validate the status code is "<sCode>"
#    And validate the "<message>" after authentication
#    And get data if "<message>" for other request
    Examples:
      | endpoint | input | sCode | message |
      | register    | ValidInputRegister    | 200 | |
      | register    | InputSameDataRegister | 400 | |
      | login       | ValidInputLogin       | 200 | |
      | login       | InputInvalidEmail     | 400 | |
      | login       | InputInvalidPassword  | 400 | |
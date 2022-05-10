Feature: Authentication
  As a user


  Scenario Outline: authentication
    Given I set an "<endpoint>" for authentication
    When I request "<input>" POST authentication
    Then I validate the status code is "<sCode>"
    And validate the "<message>" after authentication
#    And get data if "<message>" for other request
    Examples:
      | endpoint | input | sCode | message |
      | register    | InputValidRegister    | 200 | AccountRegister|
      | register    | InputRegisterNullEmail | 400 | AccountInvalid|
      | register    | InputRegisterNullPassword | 400 | AccountInvalid|
      | login       | ValidInputLogin       | 200 | AccountLogin|
      | login       | InputInvalidEmail     | 400 | AccountInvalidEmail|
      | login       | InputInvalidPassword  | 400 | AccountInvalidPassword|

#   ----- Register -----
#  Input valid register
#  Input Null email in register
#  Input Null password in register
#   ----- Login ----
#  Input valid login
#  Input invalid email & valid password
#  Input valid email & invalid password


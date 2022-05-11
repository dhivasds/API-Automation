Feature: Authentication
  As a user


  Scenario Outline: authentication
    Given I set an "<endpoint>" for authentication
    When I request "<input>" POST authentication
    Then I validate the status code is "<sCode>"
    And validate the data detail "<message>" after authentication
    Examples:
      | endpoint | input | sCode | message |
      | register    | InputValidRegister        | 200 | AccountRegister              |
      | register    | InputRegisterNullEmail    | 400 | AccountInvalidEmail          |
      | register    | InputRegisterNullPassword | 400 | AccountInvalidPassword       |
      | login       | ValidInputLogin           | 200 | AccountLogin                 |
      | login       | InputInvalidEmail         | 400 | AccountInvalidRecordNotFound |
      | login       | InputInvalidPassword      | 400 | AccountInvalidEmailOrPassword|

#   ----- Register -----
#  Input valid register
#  Input Null email in register
#  Input Null password in register
#   ----- Login ----
#  Input valid login
#  Input invalid email & valid password
#  Input valid email & invalid password


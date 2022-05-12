Feature: Rantings
  As a user
  I want to rantings products
  So that i can give ranting products

  Scenario Outline: Rantings Functionality
    Given I set an "<endpoint>" for ratings
    When I request "<inputR>" ratings
    Then I validate the status code is "<sCode>"
    And validate the data detail after "<message>" ratings
    Examples:
      | endpoint | inputR | sCode | message |
      | validId  | validGetRantingsById | 200 | GetRatingsById|
      | validId  | validGiveRantingsById   | 200 | DetailRantingProductById|
      | invalidId| invalidGiveRantingsById | 500 | ErrorRecordNotFound|
      | validId  | invalidToken        | 401 | ErrorInvalidToken|

#  ---- GET -----
#  Get All Rantings
#  ---- POST ----
#  Give Rating to product with valid Id & valid token
#  Give Rating to product with invalid Id & valid token
#  Give Rating to product with invalid token & valid Id

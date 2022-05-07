Feature: a
  aaaa
  aa


#  Scenario Outline: Product
##    Given I set an endpoint for "<products>" user
#    Given I set an "<endpoint>" for products
#    When I request "<inputP>" products
#    Then I validate the status code is "<sCodeP>" in products
#    And validate the data detail after "<statusP>" products
#    Examples:
#      | endpoint | inputP | sCodeP | statusP |
#      |     products            | validGetAllProducts   | 200 | getProducts          |
#      |     productsByValidId   | validGetProductById   | 200 | getProductById       |
#      |     productsByInvalidId | invalidGetProductById | 404 | failedGetProductById |
#      |     products            | validCreateProduct    | 200 | createProduct        |
#      |     products            | invalidCreateProduct  | 400 | failedCreateProduct  |

# Get All Product
# (+)
    Scenario: As a user i want get all products
      Given I set an endpoint for products list
      When I request GET products list
      Then I validate the status code is 200
      And validate the data detail after get products

# Get Product by ID
# (+)
    Scenario: As a user i want to get detail product
      Given I set an endpoint for products id
      When I request GET products list
      Then I validate the status code is 200
      And validate the data detail after get detail products
# (-)
  Scenario: As a user i can't to get detail product
    Given I set an endpoint for invalid products id
    When I request GET products list
    Then I validate the status code is 404
    And validate the data detail after failed to get detail products





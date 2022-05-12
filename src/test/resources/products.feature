Feature: Products


  Scenario Outline: Product Functionality
    Given I set an "<endpoint>" for products
    When I request "<inputP>" products
    Then I validate the status code is "<sCodeP>" in products
    And Validate the data detail after "<statusP>" products
    Examples:
      | endpoint | inputP | sCodeP | statusP |
      |     products           | validGetAllProducts   | 200 | getProducts          |
      |     productsByValidId  | validGetProductById   | 200 | getProductById       |
      |     productsByInvalidId| invalidGetProductById | 404 | failedGetProductById |
      |     products           | validCreateProduct    | 200 | createProduct        |
      |     products           | invalidCreateProduct  | 400 | failedCreateProduct  |

# ---- GET ----
#  Get all products
#  Get Product by ID
#  Get product by Invalid ID
#  ---- POST ----
#  Create Product
#  Invalid Create Product




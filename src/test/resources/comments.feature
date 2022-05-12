Feature: Comments
  As an user
  I want to comments a products
  So that i can give comments to products

  Scenario Outline: Comments Functionality
    Given I set an endpoint for comments
    When I request "<inputC>" comments
    Then I validate the status code is "<sCode>"
    And validate the data detail after "<message>" comments
    Examples:
      | inputC | sCode | message |
      | GiveCommentWithValidInput| 200  | InputValidComment |
      | GiveCommentWithNullInput | 500  | InputNullComment  |
      | InvalidToken             | 401  | InvalidToken      |
      | GetAllComments           | 200  | GetAllCommentsById|

#   ---- POST -----
# Give Comment to a product with valid input
# Give Comment to a product with null input
# Give Comment to a product with invalid token
#   ---- GET -----
#  Get all comments by Id



#  Kenapa POST terlebih dahulu, karna saya ingin memberi comments kepada product di feature sebelumnya baru saya GET.
#  Kalau saya langsung GET maka comments tidak akan muncul / null
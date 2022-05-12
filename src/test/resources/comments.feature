Feature: Comments
  As aa


  Scenario Outline: Comments
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


#   ---- GET -----



#  Kenapa POST terlebih dahulu, karna saya ingin memberi comments kepada product di feature sebelumnya baru saya GET.
#  Kalau saya langsung GET maka comments tidak akan muncul / null
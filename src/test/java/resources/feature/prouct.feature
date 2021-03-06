Feature: Testing different request on the bestBuy product application


Scenario: Check if the bestBUy application can be accessed by users
When Users sends a get request to products endpoint, they must get back a  valid status code 200

Scenario Outline: Create a new categories & verify if the product is added
  When I create a new product by providing the information name type  upc  price "<price>" description "<description>" model
  Then I verify the product is created
Examples:
  Examples:
    | price | description                             |
    | 70.99 | This request for creating a new product |

  Scenario: Getting product information by Id
        When I send GET request to product endpoint with Id "Id" , I should received the product information

    Scenario: Update a created product & verify if the product is updated
      When  I update a created product providing the new name upc price and description
      Then I verify the product is updated

  Scenario: Delete a created product & verify if the product is deleted
    When  I delete a created product , they must get back a valid status code is 200
    Then I verify the product is deleted

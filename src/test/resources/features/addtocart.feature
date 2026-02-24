Feature: Add products to cart from Product Shop page

  As a logged-in customer
  I want to add products from a selected department category
  So that I can purchase them

  Background:
    Given the customer is logged in
    And the customer has selected a department category
    And the customer is on the Product Shop page for that category
    And the cart item count is 0

  Scenario: Add a product to cart with default quantity
    When the customer clicks on the "Add to Cart" button for the product
    Then the product should be added to the cart
    And the cart item count should increase by 1


  Scenario: Add multiple different products to cart
    Given multiple different products are displayed
    When the customer adds the first product to the cart
    And the customer adds the second product to the cart
    Then the cart item count should equal 2



Feature: Test Web Shop

  Scenario: Using promo code gives me 15% discount
    Given I am logged in using "asfdasf@email.com" and "rJ2CB*#Hj66^cvyj^6"
    When I add item to the basket
    And I go to cart
    And I use promo code "edgewords"
    Then I get 15% discount

  Scenario: Making order creates entry in My Orders section
    Given I am logged in using "asfdasf@email.com" and "rJ2CB*#Hj66^cvyj^6"
    When I add item to the basket
    And I go to cart
    And I go to checkout
    When I checkout
    And I go to My Orders
    Then my order is visible

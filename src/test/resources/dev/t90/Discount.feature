Feature: Test Web Shop

  Scenario: Using promo code gives me 15% discount
    Given I am logged in using "username" and "password"
    And I am on the "Shop" page
    When I add item to the basket
    And I use promo code "edgewords"
    Then I get 15% discount

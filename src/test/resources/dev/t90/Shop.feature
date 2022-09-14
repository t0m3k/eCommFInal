Feature: Test Web Shop

  Scenario: Using promo code gives me 15% discount
    Given I am logged in using "asfdasf@email.com" and "rJ2CB*#Hj66^cvyj^6"
    When I add 1 item(s) to the basket
    And I use promo code "edgewords"
    Then I get 15% discount

  Scenario: Making order creates entry in My Orders section
    Given I am logged in using "asfdasf@email.com" and "rJ2CB*#Hj66^cvyj^6"
    When I add 1 item(s) to the basket
    And I checkout
    Then my order is visible

    Scenario: I remove objects from basket
      Given I am logged in using "asfdasf@email.com" and "rJ2CB*#Hj66^cvyj^6"
      When I add 5 item(s) to the basket
      And I remove all items from basket
      Then there is nothing in basket
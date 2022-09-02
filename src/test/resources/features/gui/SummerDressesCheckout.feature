Feature: Summer Dresses Checkout

  @gui
  Scenario Outline: User should able to purchase dresses
    Given User hovers the "<tab>" tab and click "<dress>"
    And Change the view of the products to List view
    And Add <product> to the cart
    And Proceed to checkout
    And Increase the quantity to <quantity>
    And Log in using "<username>" and "<password>"
    And Confirm "<address>" and shipping
    When User checkout
    Then Console should display "<name>" and <price>

    Examples:
      | tab     | dress          | product | quantity | username          | password  | address             | name                 | price  |
      | DRESSES | SUMMER DRESSES | 5       | 2        | gechici@gmail.com | Haydar.32 | 7 Montgomery Street | Printed Summer Dress | $59.96 |
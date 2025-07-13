@order_pricing
Feature: E-commerce Order Pricing Promotions
  As a shopper
  I want the system to calculate my order total with applicable promotions
  So that I can understand how much to pay and what items I will receive

  Scenario: Single product without promotions
    Given no promotions are applied
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | T-shirt     | 1        | 500       |
    Then the order summary should be:
      | totalAmount |
      | 500         |
    And the customer should receive:
      | productName | quantity |
      | T-shirt     | 1        |

  Scenario: 雙十一優惠 - 購買12件相同商品，其中10件打八折
    Given a customer places an order with:
      | productName | quantity | unitPrice |
      | T-shirt     | 12       | 100       |
    And the double-eleven promotion is enabled
    When the order is processed
    Then the order summary should be:
      | totalAmount |
      | 1000        |
    # 10*100*0.8 + 2*100 = 800 + 200 = 1000

  Scenario: 雙十一優惠 - 購買27件相同商品，其中20件打八折
    Given a customer places an order with:
      | productName | quantity | unitPrice |
      | T-shirt     | 27       | 100       |
    And the double-eleven promotion is enabled
    When the order is processed
    Then the order summary should be:
      | totalAmount |
      | 2300        |
    # 10*100*0.8 + 10*100*0.8 + 7*100 = 800 + 800 + 700 = 2300

  Scenario: 雙十一優惠 - 購買10件不同商品，沒有折扣
    Given a customer places an order with:
      | productName | quantity | unitPrice |
      | T-shirt-A   | 1        | 100       |
      | T-shirt-B   | 1        | 100       |
      | T-shirt-C   | 1        | 100       |
      | T-shirt-D   | 1        | 100       |
      | T-shirt-E   | 1        | 100       |
      | T-shirt-F   | 1        | 100       |
      | T-shirt-G   | 1        | 100       |
      | T-shirt-H   | 1        | 100       |
      | T-shirt-I   | 1        | 100       |
      | T-shirt-J   | 1        | 100       |
    And the double-eleven promotion is enabled
    When the order is processed
    Then the order summary should be:
      | totalAmount |
      | 1000        |
    # 10 * 100 = 1000  
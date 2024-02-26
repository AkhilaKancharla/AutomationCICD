
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Website

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Loggedin with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName>  and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name                  |     password       | productName  |
      | automate111@gmail.com |     Akhila@1997    | ZARA COAT 3 |
      

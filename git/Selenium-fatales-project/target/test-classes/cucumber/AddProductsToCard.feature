@tag
Feature: Add several products to card

Background:
Given User landed on Autentification pages

  @AddToCard
  Scenario Outline: Add products to card
    Given User connected with valid <email> and <password>
    When User click on the "PARFUM" category
    When User add "<productNames>" with <quantities> and "<contenance>"
    Then Products are added to card

    Examples: 
      | email                    | password          | productNames                                           | quantities| contenance  | 
      | kahlouchmamado@gmail.com | MEDKHL@ssw0rd     | ARMANI CODE COFFRET PARFUM, ARMANI CODE EAU DE TOILETTE| 4,3       | 75 ML, 75 ML|


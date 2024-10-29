@tag
Feature: Login to site with valid credentials

Background:
Given User landed on Autentification pages

  @tag2
  Scenario Outline: Positif Test of Autentification
    Given User connected with valid <email> and <password>
    When User click on My Account button
    Then The Message "BIENVENUE, MOHAMED KAHLA" displayed

    Examples: 
      | email                    |          password |
      | kahlouchmamado@gmail.com |     MEDKHL@ssw0rd | 


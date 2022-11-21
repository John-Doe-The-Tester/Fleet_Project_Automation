Feature: Create Car

  Background:
    Given The user is on the login page


  @createCar
  Scenario: Create car as a Store Manager
    And The user logs in as a "store manager"
    And the user navigates to "Fleet" "Vehicles"
    When the user clicks on Create Car button
    And the user enters new Car information
    Then the user clicks on save changes button










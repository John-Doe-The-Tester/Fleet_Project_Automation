@wip
Feature: Last Odometer
  User Story :
  As a user, I should be able to use "Last Odometer" filter under 'Fleet-Vehicles' page

  Acceptance Criteria:
  1- "Last Odometer" filter has the methods below:
  Between
  Not Between
  Equals
  Not Equals
  More Than
  Less Than
  Equals or More Than
  Equals or Less Than
  Is Empty
  Is Not Empty

  2- When user selects "Between" method with numeric values, the results should be between the specified values
  3- When user selects "Equals" method with numeric values, the results should match the specified value exactly
  4- When user selects "More than" method with numeric values, the results should be more than the specified value
  5- When user selects "Less than" method with numeric values, the results should be be less than the specified value
  6- When user selects "Is Empty" method, only empty values should be displayed.
  7- Methods ("Between","Equals","More Than","Less Than") shouldn't accept non-numeric values

  Background:
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module
    And The user clicks on "Filters" button from the top right menu
    And Manage Filter button shows up
    And The user clicks on Manage Filter button
    And The user selects "Last Odometer" filter

  Scenario: AC-1: Last Odometer Methods
    When The user clicks on methods
    Then The user must see the methods below
      | Between             |
      | Not Between         |
      | Equals              |
      | Not Equals          |
      | More Than           |
      | Less Than           |
      | Equals Or More Than |
      | Equals Or Less Than |
      | Is Empty            |
      | Is Not Empty        |

  Scenario: AC-2: "Between" method with numeric values
    When The user clicks on methods
    And The user selects "Between" method
    And The user enters "1000" and "5000"
    Then All the results in the table are between "1000" and "5000"

  Scenario: AC-3: "Equals" method with numeric values
    When The user clicks on methods
    And The user selects "Equals" method
    And The user enters "1000"
    Then All the results in the table are equals to "1000"

  Scenario: AC-4: "More Than" method with numeric values
    When The user clicks on methods
    And The user selects "More Than" method
    And The user enters "9000"
    Then All the results in the table are more than "9000"

  Scenario: AC-5: "Less Than" method with numeric values
    When The user clicks on methods
    And The user selects "Less Than" method
    And The user enters "3000"
    Then All the results in the table are less than "3000"

  #This scenario has bugs and fails
  Scenario: AC-6: "Is Empty" method with numeric values
    When The user clicks on methods
    And The user selects "Is Empty" method
    Then All the results in the table are empty

  #This scenario has bugs and fails
  Scenario Outline: AC-7 : Methods with non-numeric values
    When The user clicks on methods
    And The user selects "<methodName>" method
    When The user enters non-numeric "<values>"
    Then Fields shouldn't accept non-numeric values

    Examples:
      | methodName | values  |
      | Equals     | asddfdf |
      | Equals     | ^+%&/   |
      | More Than  | asddfdf |
      | More Than  | ^+%&/   |
      | Less Than  | asddfdf |
      | Less Than  | ^+%&/   |
      | Between    | asddfdf |
      | Between    | ^+%&/   |

@wip
Feature: Tags Filter
  User Story :
  As a user, I should be able to use "Tags" filter under 'Fleet-Vehicles' page

  Acceptance Criteria:
  1- "Tags" filter has the methods below:
  Is Any of
  Is not Any of
  2- When user selects "Is any Of" method with "Compact" option, the table should include corresponding value
  3- When user selects "Is not any Of" method with "Compact" option, the table shouldn't include corresponding value
  4- When user selects "Is not any Of" method with "Compact" and "Sedan", the table shouldn't include corresponding values

  Background:
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module
    And The user clicks on "Filters" button from the top right menu
    And Manage Filter button shows up
    And The user clicks on Manage Filter button
    And The user selects "Tags" filter

  Scenario: AC-1: Tags Filter Methods
    When The user clicks on methods
    Then The user must see the methods below
      | Is Any Of     |
      | Is Not Any Of |

  Scenario: AC-2: "Is Any Of" method with Compact
    When The user clicks on methods
    And The user selects "Is Any Of" method
    And The user selects "Compact" method from dropdown
    Then All the results in the table are between "1000" and "5000"








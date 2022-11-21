Feature: Filter Menu General
  User Story :
  As a user, I should be able to filter vehicle table, in Fleet-Vehicle page

  Acceptance Criteria:
  1- When user clicks on filter icon, "Manage Filter" button should be visible
  2- User can apply filters by clicking on filter name, through 'Manage Filters' menu.
  3- User can find filters by typing the filter name, through 'Manage Filters' menu.
  4- User can apply multiple filters at the same time
  5- User can remove all filters by clicking on the reset icon, under 'Fleet-Vehicles' page

  Scenario: AC-1,2,3,4,5: Verify "Manage Filter" button is visible when user clicks on filter icon
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module

    When The user clicks on "Filters" button from the top right menu
    Then Manage Filter button shows up

    When The user clicks on Manage Filter button
    And The user search for the filters below by typing
      | Tags          |
      | Driver        |
      | Location      |
      | Last Odometer |
    Then Filters must be found

    When The user clicks on the filters below
      | Tags          |
      | Driver        |
      | Location      |
      | Last Odometer |
    Then Filters must be applied on the table

    When The user clicks on "Reset" button from the top right menu
    Then All filters on the page should be removed



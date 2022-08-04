Feature: Vehicle General Information

  User Story :
  As a user, I should be able to see the detailed information of a specific vehicle (General Information Page)

  Acceptance Criteria:
  1- User can see the "General Information" page by clicking on any vehicle (row), under 'Fleet-Vehicle' module
  2- User can see the "General Information" page clicking on the "Eye (View)" icon at the end of each row, under 'Fleet-Vehicle' module
  3- Sales manager and store manager should see "Edit", "Delete" and "Add Event" buttons on the "General Information" page
  4- Driver shouldn't see "Add Event", "Edit" and "Delete" buttons
  5- Vehicle information displayed on the "General Information" page and "Fleet-Vehicle" page should be the same


  @wip
  Scenario: AC1- General Info page by clicking any row
    Given The user is on the login page
    And The user logs in as a "sales manager"
    And The user navigates to "Fleet" - "Vehicles" module

    When The user clicks on any row on the Vehicle - All Cars page
    Then The user is on the General Information page

  Scenario: AC2- General Info page by eye (view) icon
    Given The user is on the login page
    And The user logs in as a "sales manager"
    And The user navigates to "Fleet" - "Vehicles" module

    When The user hover over the three dots at the end of any row
    And The user clicks on the "View" icon
    Then The user is on the General Information page

  @wip
  Scenario Outline: AC3- Sales manager and store manager should see "Edit", "Delete" and "Add Event" buttons
    Given The user is on the login page
    And The user logs in as a "<user type>"
    And The user navigates to "Fleet" - "Vehicles" module
    When The user clicks on any row on the Vehicle - All Cars page
    Then The user can see "Edit", "Delete" and "Add Event" buttons

    Examples:
      | user type     |
      | Sales Manager |
      | Store Manager |

  @wip
#    This cenatio fails cause driver can see the add event btn
  Scenario: AC4- truck driver shouldn't see "Edit", "Delete" and "Add Event" buttons
    Given The user is on the login page
    And The user logs in as a "Driver"
    And The user navigates to "Fleet" - "Vehicles" module
    When The user clicks on any row on the Vehicle - All Cars page
    Then The user can not see "Edit", "Delete" and "Add Event" buttons

  @wip
  Scenario: AC5- Vehicle information should be the same
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module
    When The user saves car info of any row randomly
    And The user clicks on the same row on the Vehicle - All Cars page
    Then All the car info must be the same as in the General Info page
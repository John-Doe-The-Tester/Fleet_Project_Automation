Feature: Add Event

  Background:
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module
#    And The user clicks on any row on the Vehicle - All Cars page

  Scenario: AC 1 and AC 2-'View Per Page' button
    When The user looks at the View Per Page
    Then The default value is "25"

  Scenario: AC 3-'View Per Page' options
    When The user looks at the View Per Page
    Then The options are as listed below and clickable
      | 10  |
      | 25  |
      | 50  |
      | 100 |

  Scenario Outline: AC 3-'View Per Page' options - Total Results on the page
    When The user selects the "<options>" from View Per Page
    Then The user should see "<options>" results on the page

    Examples:
      | options |
      | 10      |
      | 25      |
      | 50      |
      | 100     |


  Scenario Outline: AC 4- User can sort any column
    When The user clicks on "<column>" column in the table
    Then The "<column>" column is sorted in "<order1>" order

    When The user clicks on "<column>" column again
    Then The "<column>" column is sorted in "<order2>" order

    Examples:
      | column        | order1    | order2     |
      | Model Year    | ascending | descending |
      | Driver        | ascending | descending |
      | Location      | ascending | descending |
      | Last Odometer | ascending | descending |


  @wip
  Scenario: AC 5- Remove sortings by reset button

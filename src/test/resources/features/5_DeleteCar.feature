@wip
Feature: Delete a car
  User Story :
  As a 'Sales Manager' and 'Store Manager', I should be able to delete a car

  Acceptance Criteria:
  1- All users can see the delete button by hovering over the three dots at the end of each row
  2- "Delete Confirmation" pop up should be displayed when the user clicks on the delete button
  3- "You do not have permission to perform this action." message should be displayed if the driver clicks on "Yes, Delete" button.
  4- Delete option a:
  Authorized user can delete any car, by clicking on the delete button at the end of each row, and "Item deleted" message should be displayed.
  5- Delete option b:
  Authorized user can delete any vehicle from 'General Information' page, by clicking on the 'Delete' button, and "Car deleted" message should be displayed.
  6- When a car is deleted, it must be removed from Fleet-Vehicle page.

  Background:
    Given The user is on the login page

  Scenario Outline: AC-1,2,3,4: All users can see delete button at the end of each row
    And The user logs in as a "<userType>"
    And The user navigates to "Fleet" - "Vehicles" module
    When The user hover over the three dots at the end of any row
    Then The user can see "Delete" button

    When The user clicks on the "Delete" icon
    Then Delete Confirmation pop up should be displayed

    When The user clicks on Yes-Delete btn
    Then "<userType>" gets "<message>" message

    Examples:
      | userType      | message                                            |
      | driver        | You do not have permission to perform this action. |
      | sales manager | Item deleted                                       |
      | store manager | Item deleted                                       |


  Scenario Outline: AC-5,6: Delete a car from General Info page
    And The user logs in as a "<userType>"
    And The user navigates to "Fleet" - "Vehicles" module

    When The user clicks on any row on the VehicleTable page
    Then The user is on the General Information page
    And The user saves "License Plate" of the car

    When The user clicks on "Delete" button
    When The user clicks on Yes-Delete btn
    Then "<userType>" gets "<message>" message
    And The deleted car is also removed from the table

    Examples:
      | userType      | message     |
      | sales manager | Car deleted |
      | store manager | Car deleted |


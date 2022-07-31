Feature: Vehicle Table View

  Background:
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module

  Scenario: AC 1- User can see the total page number
    Then The user can see number of total pages at the top

  @wip
  Scenario: AC 2- User can go to next and previous pages by < and > buttons
    When The user clicks on "next" page button
    Then The user goes to next page

    When The user clicks on "previous" page button
    Then The user goes to previous page

  @wip
  Scenario: AC 2- User can go to next and previous pages by < and > buttons - Negative Scenario
    When The user is on the first page of vehicles table
    Then The user can not click on previous page button

    When The user is on the last page of vehicles table
    Then The user can not click on next page button

  @wip
  Scenario: AC 3- User can see total recordings
    Then The user can see number of total recordings at the top

  @wip
  Scenario: AC 4- User can download the data
    When The user donwloads the data as a "CSV" file
    Then The user gets a success message

    When The user donwloads the data as a "XLSX" file
    Then The user gets a success message




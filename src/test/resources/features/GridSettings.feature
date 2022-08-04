#1- "Grid Settings" should be visible when user clicks on the gear icon
#2- Column names in grid settings should be shown as below:
#-Id
#-License Plate
#-Tags
#-Driver
#-Location
#-Chassis Number
#-Model Year
#-Last Odometer
#-Immatriculation Date
#-First Contract Date
#-Catalog Value (VAT Incl.)
#-Seats Number
#-Doors Number
#-Color
#-Transmission
#-Fuel Type
#-CO2 Emissions
#-Horsepower
#-Horsepower Taxation
#-Power (KW)
#3- User can find any column by typing the name on "Quick Search" input box
#4- User can select any column by clicking the column name
#5- User can arrange the order of the columns (by dragging and dropping)
#6- User can see all corresponding changes under 'Fleet-Vehicles' pages

Feature: Grid Settings

  Background:
    Given The user is on the login page
    And The user logs in as a "Sales Manager"
    And The user navigates to "Fleet" - "Vehicles" module
    And The user clicks on "Grid Settings" button from the top right menu

  Scenario: AC1- Grid Settings is visible
    Then Grid Settings menu shows up

  @wip
  Scenario: AC2- Grid Settings list options
    Then Grid Settings list options are as listed below
      | Id                        |
      | License Plate             |
      | Tags                      |
      | Driver                    |
      | Location                  |
      | Chassis Number            |
      | Model Year                |
      | Last Odometer             |
      | Immatriculation Date      |
      | First Contract Date       |
      | Catalog Value (VAT Incl.) |
      | Seats Number              |
      | Doors Number              |
      | Color                     |
      | Transmission              |
      | Fuel Type                 |
      | CO2 Emissions             |
      | Horsepower                |
      | Horsepower Taxation       |
      | Power (KW)                |


  @wip
  Scenario: AC3- "Quick Search"
    When The user types following column names into quick search field
      | Driver        |
      | Tags          |
      | License Plate |
      | Model Year    |
      | Last Odometer |
      | Seats Number  |
      | Color         |
    Then The relevant column shows up

  @wip
  Scenario: AC3- "Quick Search" Partial Search
    When The user types following column names into quick search field
      | Dri      |
      | Tag      |
      | License  |
      | Model    |
      | Last Odo |
      | Seats    |
      | Col      |
      | Ho       |
      | Fu       |
      | Ca       |
      | A        |
      | B        |
    Then The relevant column shows up





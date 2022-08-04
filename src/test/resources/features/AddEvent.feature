  #This feature is made up two different USs
  #Both of the related members should work on the same feature file
  #And same step definition class

  Feature: Add Event

    Background:
      Given The user is on the login page
      And The user logs in as a "Sales Manager"
      And The user navigates to "Fleet" - "Vehicles" module
      And The user clicks on any row on the Vehicle - All Cars page


      #AC1 and AC2 both are covered in this scenario
    Scenario: AC 1 and AC-2 - User can see the "Add Event" page from the "General Information" page
      When The user clicks on "Add Event" button
      Then "Add Event" page pops up
      And The user closes Add Event page


    Scenario: AC 3- User can mark the event with any color
      When The user clicks on "Add Event" button
      Then The user can click any of the colors
      And The user closes Add Event page


    Scenario: AC 4- User can click "All-day event"
      When The user clicks on "Add Event" button
      Then The user can click "All-day event" button
      And The user closes Add Event page


    Scenario: AC 5a- Repeat Options
      When The user clicks on "Add Event" button
      And The user can click "Repeat" button
      Then The user can click any of the repeat options below
        | Daily   |
        | Weekly  |
        | Monthly |
        | Yearly  |

    Scenario: AC 5b- Ending Options
      When The user clicks on "Add Event" button
      And The user can click "Repeat" button
      Then The user can click any of the ending options below
        | Never |
        | After |
        | By    |


      Scenario: AC - User can see events in General Info page
        Then The user can see all the events of related row-vehicle

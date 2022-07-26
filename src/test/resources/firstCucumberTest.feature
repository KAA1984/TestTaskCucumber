Feature: Distance calculation
  Scenario: User should be able to calculate distance for the trip
    Given The User is on webpage of calculation distance
    And The User click on button of looking for the route
    When The User enter data from , to
    And  Chose transport
    And Click Go button
    Then The User should see of distance

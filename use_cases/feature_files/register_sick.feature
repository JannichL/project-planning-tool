Feature: Registering sick time
  Description: A User registers sick days
  Actors: User

  Scenario: user registers sick time
    Given the user enters the sick time details
    Then its registered in the database
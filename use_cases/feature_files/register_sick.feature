Feature: Registering sick time
  Description: A User registers sick days
  Actors: User
  
  Background: There is a user in the database
    Given there is a User with the initials "huba"

  Scenario: user registers sick time
    Given the user "huba" enters the sick time details 2
    Then 2 days are registered in the database to "huba"
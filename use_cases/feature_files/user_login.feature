Feature: User login
  Description: A user (either a worker or the project manager) attempts to login providing a string of their worker ID
  Actors: User

  Background: The database has a set of worker IDs
    And these IDs are contained in the database
      | huba |
      | aha  |
      | ekki |

  Scenario: User can login
    Given the User is not logged in
    When the User tries to login with the initials "huba"
    Then the User with the initials "huba" is found
    And the User is logged in

  Scenario: User has the wrong password
    Given that the User is not logged in
    When the worker ID is "wrong ID"
    Then the User is not logged in

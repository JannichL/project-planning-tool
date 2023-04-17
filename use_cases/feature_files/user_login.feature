Feature: User login
  Description: A user (either a worker or the project manager) attempts to login providing a string of their worker ID
  Actors: User

  Background: The database has a set of worker IDs
    Given that the User is logged in
    And these IDs are contained in the database
      | huba |
      | aha |
      | ekki |
    And the User logs out

  Scenario: User can login
    Given that the User is not logged in
    And the worker ID is "correct ID"
    Then the User login succeeds
    And the User is logged in

  Scenario: User has the wrong password
    Given that the User is not logged in
    And the worker ID is "wrong ID"
    Then the User login fails
    And the User is not logged in

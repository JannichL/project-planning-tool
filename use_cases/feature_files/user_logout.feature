Feature: User logout
  Description: The user (either a worker or the project manager) logs out from the project planning tool system
  Actor: User

  Background: That the user is already logged in
    Given these IDs are contained in the database
      | huba |
      | aha  |
      | ekki |
    And the User is logged in with the initials "huba"

  Scenario: User logs out
    Given the User is already logged in
    When the User logs out
    Then the User is not logged in

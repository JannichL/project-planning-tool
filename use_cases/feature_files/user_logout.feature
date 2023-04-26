Feature: User logout
  Description: The user (either a worker or the project manager) logs out from the project planning tool system
  Actor: User

  Scenario: User logs out
    Given the User is already logged in
    When the User logs out
    Then the User is not logged in

Feature: User logs hours
  Description: A user (either a worker or the project manager) logs the working hours he has committed to
  an assignment on a given project

  Scenario: User trying to view log additional hours
    Given the User is logged in
    When the User navigates to "Log Additional Hours"
    And that there are projects available
    Then the system displays a list of available Projects

  Scenario: User logs additional hours
    Given that there are projects available
    When the User inputs their logged hours
    Then the system will register their logged hours


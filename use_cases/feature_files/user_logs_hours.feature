Feature: User logs hours
  Description: A user (either a worker or the project manager) logs the working hours he has committed to
  an assignment on a given project

  Scenario: User logs additional hours
    Given that there are projects available
    And there is a User with the initials "huba"
    When the User logs 2 hours of work on a project with the ID "Skyscraper" on task "Design"
    Then the system will register their logged hours "2" and project ID "Skycraper" and Task "Design"


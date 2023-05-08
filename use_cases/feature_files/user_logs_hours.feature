Feature: User logs hours
  Description: A user (either a worker or the project manager) logs the working hours he has committed to
  an assignment on a given project

  Background: There exists a project
    Given there exists a project with the name "Skyscraper"
    And the project has a task called "Design" with startWeek 1, endWeek 4, budgettedHours 100

  Scenario: User logs additional hours
    Given there is a User with the initials "huba"
    When the User logs 2 hours of work on task "Design" in project with the name "Skyscraper"
    Then the system will register 2 hours of work on task "Design" in project with the name "Skyscraper"


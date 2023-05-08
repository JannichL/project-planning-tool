Feature: Delete a task
  Description: A task is removed from a project
  Actors: User

  Background:
    Given there is a project called "Test project"
    And user "huba" and "ekki" exists in the database
    And the current user is "huba"

  Scenario: Delete a task successfully if no ProjectManager is selected
    Given the Project has a task called "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And there is no ProjectManager elected
    When the User tries to delete task called "Design"
    Then the task with title "Design" is not contained in the project

  Scenario: Delete a task successfully if a ProjectManager is selected
    Given the Project has a task called "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And there is a ProjectManager elected
    And the User is the ProjectManager
    When the User tries to delete task called "Design"
    Then the task with title "Design" is not contained in the project

  Scenario: Delete a task when the assigned ProjectManager is not logged in
    Given the Project has a task called "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And there is a ProjectManager elected
    And the User is not the ProjectManager
    When the User tries to delete task called "Design"
    Then the error message "Only the project manager can delete tasks!" is given

  Scenario: Delete a task that does not exists in project
    Given the user has access to deleting tasks
    And a task called "Design" does not exist in the project
    When the User tries to delete task called "Design"
    Then the error message "Task does not exist!" is given

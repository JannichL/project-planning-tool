Feature: Create a task
  Description: A task is added to a project
  Actors: User

  Background:
    Given there is a project called "Test project"
    And user "huba" and "ekki" exists in the database
    And the current user is "huba"

  Scenario: Create a task successfully if no ProjectManager is selected
    Given there is no ProjectManager elected
    And the User creates a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is not contained in the project
    When the task is added to the project
    Then the task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14 is contained in the project

  Scenario: Create a task successfully if a ProjectManager is selected
    Given there is a ProjectManager elected
    And the User is the ProjectManager
    And the User creates a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is not contained in the project
    When the task is added to the project
    Then the task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14 is contained in the project

  Scenario: Create a task when the assigned ProjectManager is not logged in
    Given there is a ProjectManager elected
    And the User is not the ProjectManager
    And the User creates a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is not contained in the project
    When the task is added to the project
    Then the error message "Only the project manager can add tasks!" is given

  Scenario: Create a task that already exists in project
    Given the user has access to creating tasks
    And the User creates a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is already contained in the project
    When the task is added to the project
    Then the error message "Task already exists!" is given

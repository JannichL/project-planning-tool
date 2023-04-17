Feature: Create a task
  Description: A task is added to a project
  Actors: User

  Scenario: Create a task successfully if no ProjectManager is selected
    //Given that the User is logged in
    Given there is no ProjectManager elected
    And there is a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is not contained in the project
    When the task is added to the project
    Then the task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14 is contained in the project

  Scenario: Create a task successfully if a ProjectManager is selected
    //Given that the User is logged in
    Given there is a ProjectManager elected
    And the User is the ProjectManager
    And there is a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is not contained in the project
    When the task is added to the project
    Then the task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14 is contained in the project

  Scenario: Create a task when the assigned ProjectManager is not logged in
    //Given that the User is logged in
    Given there is a ProjectManager elected
    And the User is not the ProjectManager
    And there is a task with title "Design", budgetedHours 100, startWeek 12, and endWeek 14
    And the task is not contained in the project
    When the task is added to the project
    Then the error message "Only project manager can create tasks" is given

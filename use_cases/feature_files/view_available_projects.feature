Feature: View available projects
Description: A user searches for a project by looking through the created projects
Actors: User

  Background: The database has a set of Projects
    Given projects with these names are contained in the database
      | Project 1 |
      | Project 2 |
      | Project 3 |
      | Project 4 |

Scenario: Viewing available projects
  Then “Project1”, “Project2”, “Project3” and “Project4” are shown

Scenario: No available projects
Given that there are no projects available
Then no projects will be shown

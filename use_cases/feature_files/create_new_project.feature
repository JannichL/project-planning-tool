Feature: Create a new project
  Description: A new project is set up
  Actors: User

  Background: The database has a set of Projects
    Given these Projects are contained in the database
      | Project 1 |
      | Project 2 |
      | Project 3 |
      | Project 4 |

  Scenario: Create a new project successfully
  Given a project with "Project5" name is not in the database
  When the project "Project5" is created its stored in the database

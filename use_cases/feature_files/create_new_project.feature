Feature: Create a new project
  Description: A new project is set up
  Actors: User

  Scenario: Create a new project successfully
  Given that the User is logged in.
  And a project with that name is not in the database.
  When the project is added to the database
  Then the project is stored in the database

  Scenario: Create a new project and the User is not logged in
  Given that the User is not logged in.
  And there is a project that needs to be created.
  And a project with that name is not in the database.
  When the project is added to the database
  Then the error message "User login required" is given
